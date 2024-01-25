package com.example.educationappmvvm.presenter.screen.group

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.educationappmvvm.R
import com.example.educationappmvvm.presenter.data.model.GroupData

class GroupViewModel {

    private val model: GroupModel = GroupModel()

    private val _group = MutableLiveData<List<GroupData>>()
    val group: LiveData<List<GroupData>> get() = _group

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    init {
        loadGroup()
    }

    fun loadGroup() {
        val ls = model.allGroup()
        _isEmpty.value = ls.isEmpty()
        _group.value = ls
    }

    fun addGroup(data: GroupData) {
        model.addGroup(data)
        loadGroup()
    }

    fun updateGroup(data: GroupData) {
        model.updateGroup(data)
        loadGroup()
    }

    fun deleteGroup(data: GroupData) {
        model.deleteGroup(data)
        loadGroup()
    }
}