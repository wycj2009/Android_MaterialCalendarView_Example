package com.example.android_materialcalendarview_example

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainMaterialcalendarviewCalendar: MaterialCalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainMaterialcalendarviewCalendar = findViewById(R.id.activity_main_materialcalendarview_calendar)

        //토요일 색상 세팅
        val saturdayDecorator = object : DayViewDecorator {
            private val calendar = Calendar.getInstance()

            override fun shouldDecorate(day: CalendarDay): Boolean {
                day.copyTo(calendar)
                return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
            }

            override fun decorate(view: DayViewFacade) {
                view.addSpan(ForegroundColorSpan(Color.BLUE))
            }
        }

        //일요일 색상 세팅
        val sundayDecorator = object : DayViewDecorator {
            private val calendar = Calendar.getInstance()

            override fun shouldDecorate(day: CalendarDay): Boolean {
                day.copyTo(calendar)
                return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
            }

            override fun decorate(view: DayViewFacade) {
                view.addSpan(ForegroundColorSpan(Color.RED))
            }
        }

        //선택 날짜 배경 세팅
        val selectedDateDecorator = object : DayViewDecorator {
            private val drawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.selector_date_checked)!!

            override fun shouldDecorate(day: CalendarDay): Boolean {
                return true
            }

            override fun decorate(view: DayViewFacade) {
                view.setSelectionDrawable(drawable)
            }
        }

        //오늘 날짜 텍스트 세팅
        val todayDecorator = object : DayViewDecorator {
            private val today = CalendarDay.today()

            override fun shouldDecorate(day: CalendarDay): Boolean {
                return day == today
            }

            override fun decorate(view: DayViewFacade) {
                view.addSpan(StyleSpan(Typeface.BOLD))
                view.addSpan(RelativeSizeSpan(1.75f))
                view.addSpan(ForegroundColorSpan(Color.BLACK))
            }
        }

        //점 찍기
        val dotDecorator = object : DayViewDecorator {
            override fun shouldDecorate(day: CalendarDay): Boolean {
                return true
            }

            override fun decorate(view: DayViewFacade) {
                view.addSpan(DotSpan(5f, getColor(R.color.black)))
            }
        }

        activityMainMaterialcalendarviewCalendar.addDecorators(saturdayDecorator, sundayDecorator, selectedDateDecorator, todayDecorator, dotDecorator) //데코레이터 적용

        activityMainMaterialcalendarviewCalendar.selectedDate = CalendarDay.today() //현재 선택 날짜 세팅
    }

}