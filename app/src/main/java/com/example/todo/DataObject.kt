package com.example.todo


object DataObject {
    var listdata = mutableListOf<CardInfo>()

    fun setData(task: CardInfo) {
        listdata.add(task)
    }

    fun getAllData(): List<CardInfo> {
        return listdata
    }

    fun deleteAll() {
        listdata.clear()
    }

    fun getData(id: String): CardInfo {
        val task = listdata.find { it.id == id }
        return task!!
    }

    fun deleteData(id: String) {
        listdata.removeIf { it.id == id }
    }

    fun updateData(id: String, title: String, priority: String) {
        val tmp = listdata.find { it.id == id }
        val id = listdata.indexOf(tmp)
        listdata[id].title = title
        listdata[id].priority = priority
    }

}