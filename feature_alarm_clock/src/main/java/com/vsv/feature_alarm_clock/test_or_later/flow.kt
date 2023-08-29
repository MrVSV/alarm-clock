package com.vsv.feature_alarm_clock.test_or_later

/**
 * есть модель AlarmItem
 * у нее есть рингтон
 * чтобы поставить рингтон нужен контекст
 * есть 2 экрана: список итемов и список рингтонов, у каждого свой стейт
 * дефолтный рингтон определяется системой
 * далее дефолтный рингтон заменяется тем, который был выбран последним
 * получить последний выбранный можно из экрана с рингтонами
 * какой порядок действий?
 * открывается стартовый экран
 * при добавлении аларма должнен назначится рингтон +
 *
 * */

fun main() {

    /**число - день недели, значение - должен ли играть будильник*/
    val alarmDays =
        mapOf(1 to false, 2 to true, 3 to true, 4 to false, 5 to false, 6 to false, 7 to true)

    repeat  (10) {
        val today = listOf(2, 3, 7).random() /**день в который сработал будильник*/
        val falseList = mutableListOf<Int>()
        println("=============")
        println("today = $today")
        var i = if (today == 7) 1 else today + 1
        repeat (7) {
            if(alarmDays[i] == false) {
                falseList.add(i) /** просто чтоб видеть, через какие дни прошел*/
                i = if (i == 7) 1 else i + 1
            } else  return@repeat
        }
        println(falseList) /** просто чтоб видеть, через какие дни прошел*/
        println("$i - ${alarmDays[i]}") /** следующий день срабатывания будильника*/
    }
}


