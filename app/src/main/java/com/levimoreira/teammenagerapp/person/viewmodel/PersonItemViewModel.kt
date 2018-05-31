package com.levimoreira.teammenagerapp.person.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import com.levimoreira.teammenagerapp.application.entities.Person
import com.levimoreira.teammenagerapp.person.data.PersonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PersonItemViewModel @Inject constructor(var personRepository: PersonRepository) : ViewModel() {

    fun createPerson(person: Person): LiveData<Long> {
        val result = personRepository
                .insertPerson(person)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable()

        return LiveDataReactiveStreams.fromPublisher(result)
    }


}