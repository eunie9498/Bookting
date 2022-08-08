package com.bookting.view.main.home

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bookting.R
import com.bookting.data.HOME
import com.bookting.data.MainConstants
import com.bookting.databinding.HomeAnalyHolderBinding
import com.bookting.utils.dpToPx
import com.bookting.utils.getCurrentTime
import com.bookting.utils.getDayOfTime
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.IMarker
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.renderer.DataRenderer
import com.github.mikephil.charting.renderer.XAxisRenderer
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Transformer
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler

class HomeAnalysis(val nick: String, val binding: HomeAnalyHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var selected = ""

    fun bind(item: HOME.Analysis) = with(binding) {
        tvRecomm.text =
            root.context.getString(R.string.home_book_analy, item.data.total_amount)

        val labels = arrayOf(
            MainConstants.MON, MainConstants.TUE, MainConstants.WED,
            MainConstants.THU, MainConstants.FRI, MainConstants.SAT, MainConstants.SUN
        )

        val txtArr= ArrayList<String>()
        item.data.contents.forEach {
            txtArr.add(it.amount.toString())
        }

        val bds = ArrayList<IBarDataSet>()

        val entryArr = ArrayList<BarEntry>()
        var i = 0

        item.data.contents.forEach {
            entryArr.add(BarEntry(i.toFloat(), it.amount.toFloat()))
            i++
        }

        val bds1 = BarDataSet(entryArr, "")
        bds1.setColors(
            ContextCompat.getColor(binding.root.context, R.color.blue600),
            ContextCompat.getColor(binding.root.context, R.color.blue600),
            ContextCompat.getColor(binding.root.context, R.color.blue600),
            ContextCompat.getColor(binding.root.context, R.color.blue600),
            ContextCompat.getColor(binding.root.context, R.color.blue600),
            ContextCompat.getColor(binding.root.context, R.color.blue600),
            ContextCompat.getColor(binding.root.context, R.color.blue600)
        )
        bds.add(bds1)

        val barData = BarData(bds)
        barData.barWidth = 0.4F
        barData.setDrawValues(false)

        barChart.apply {
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return labels[value.toInt()]
                }
            }

            val yAxis: YAxis = axisLeft
            yAxis.axisMinimum = 0f
            yAxis.axisMaximum = (item.data.contents.maxOf { it.amount }.plus(10L)).toFloat()

            xAxis.position = XAxis.XAxisPosition.BOTTOM

            axisLeft.setDrawGridLines(false)
            axisLeft.setDrawAxisLine(false)
            axisLeft.setDrawLabels(false)

            axisRight.setDrawGridLines(false)
            axisRight.setDrawAxisLine(false)
            axisRight.setDrawLabels(false)

            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)

            description = null
            data = barData
            legend.isEnabled = false

            setPinchZoom(false)
            setScaleEnabled(false)
            isDoubleTapToZoomEnabled = false
            setTouchEnabled(false)
        }

        bds1.highLightColor = ContextCompat.getColor(binding.root.context, R.color.blue600)
        bds1.highLightAlpha = 255

        selected = getDayOfTime(getCurrentTime())
        labels.indexOf(selected)
        val highlight = Highlight(labels.indexOf(selected).toFloat(), 50F, 0)
        highlight.dataIndex = labels.indexOf(selected)

        binding.barChart.highlightValue(highlight)
        binding.barChart.xAxis.setDrawGridLines(false)

        invali(entryArr)

        binding.barChart.invalidate()
    }

    private fun invali(arr: ArrayList<BarEntry>) {

        binding.barChart.setXAxisRenderer(
            ColoredLabelXAxisRenderer(
                binding.barChart.viewPortHandler,
                binding.barChart.xAxis,
                binding.barChart.getTransformer(YAxis.AxisDependency.LEFT),
                arrayListOf(
                    ContextCompat.getColor(binding.root.context, R.color.grey800),
                    ContextCompat.getColor(binding.root.context, R.color.red700)
                ),
                selected,
                binding.barChart,
                arr,
                binding.root.context
            )
        )

        val marker: IMarker = YourMarkerView(
            binding.root.context,
            R.layout.graph_marker
        )
        binding.barChart.marker = marker
    }

    inner class YourMarkerView(
        context: Context?,
        layoutResource: Int
    ) : MarkerView(context, layoutResource) {
        private var tvContent: TextView? = null

        override fun refreshContent(e: Entry, highlight: Highlight) {
            tvContent!!.text = (e.y.toInt()).toString()+"권"

            if (e.x == highlight.x)
                tvContent!!.setTextColor(ContextCompat.getColor(context, R.color.blue600))

            super.refreshContent(e, highlight)
        }

        private var mOffset: MPPointF? = null
        override fun getOffset(): MPPointF {
            if (mOffset == null) {
                mOffset = MPPointF(
                    (-(width / 2)).toFloat(),
                    -50F
                )
            }
            return mOffset!!
        }

        init {
            tvContent = findViewById(R.id.tvMarker)
        }
    }


    inner class ColoredLabelXAxisRenderer(
        viewPortHandler: ViewPortHandler?,
        xAxis: XAxis?,
        trans: Transformer?,
        colors: List<Int>,
        selected: String,
        barChart: BarChart,
        arr: ArrayList<BarEntry>,
        context: Context,
    ) : XAxisRenderer(viewPortHandler, xAxis, trans) {
        var labelColors: List<Int> = colors
        var select = selected
        var context = context

        override fun drawLabels(c: Canvas?, pos: Float, anchor: MPPointF?) {
            val labelRotationAngleDegrees = mXAxis.labelRotationAngle
            val centeringEnabled = mXAxis.isCenterAxisLabelsEnabled
            val positions = FloatArray(mXAxis.mEntryCount * 2)
            run {
                var i = 0
                while (i < positions.size) {
                    if (centeringEnabled) {
                        positions[i] = mXAxis.mCenteredEntries[i / 2]
                    } else {
                        positions[i] = mXAxis.mEntries[i / 2]
                    }
                    i += 2
                }
            }

            mTrans.pointValuesToPixel(positions)
            var i = 0
            while (i < positions.size) {
                var x = positions[i]
                if (mViewPortHandler.isInBoundsX(x)) {
                    val label =
                        mXAxis.valueFormatter.getFormattedValue(mXAxis.mEntries[i / 2], mXAxis)
                    val color = getColorForXValue(mXAxis.mEntries[i / 2].toInt()) //added
                    mAxisLabelPaint.color = color

                    if (label == select) {
                        mAxisLabelPaint.typeface = Typeface.DEFAULT_BOLD
                    } else {
                        mAxisLabelPaint.typeface = Typeface.DEFAULT
                    }

                    if (mXAxis.isAvoidFirstLastClippingEnabled) {
                        if (i == mXAxis.mEntryCount - 1 && mXAxis.mEntryCount > 1) {
                            val width: Float = Utils.calcTextWidth(mAxisLabelPaint, label).toFloat()
                            if (width > mViewPortHandler.offsetRight() * 2
                                && x + width > mViewPortHandler.chartWidth
                            ) x -= width / 2

                        } else if (i == 0) {
                            val width: Float = Utils.calcTextWidth(mAxisLabelPaint, label).toFloat()
                            x += width / 2
                        }
                    }
                    drawLabel(c, label, x, pos, anchor, labelRotationAngleDegrees)
                }
                i += 2
            }
        }

        private fun getColorForXValue(index: Int): Int {
            return if (index == 6) labelColors[1]
            else labelColors[0]
        }
    }
}
