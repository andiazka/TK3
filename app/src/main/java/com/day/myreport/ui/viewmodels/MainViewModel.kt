package com.day.myreport.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.day.myreport.MyReport
import com.day.myreport.database.DbHelper

class MainViewModel() : ViewModel(), DbHelper.DatabaseCallback {
    private var dbHelper: DbHelper = DbHelper(MyReport.getInstance().applicationContext,this)
    private val _textList = MutableLiveData<List<String>>()
    val textList: LiveData<List<String>> get() = _textList

    init {
        refreshData()
    }

    override fun onTextInserted() {
        refreshData()
    }

    fun refreshData() {
        val newTextList = dbHelper.getAllTexts()
        _textList.value = newTextList
    }

    fun insertText(text: String) {
        dbHelper.insertText(text)
    }


}