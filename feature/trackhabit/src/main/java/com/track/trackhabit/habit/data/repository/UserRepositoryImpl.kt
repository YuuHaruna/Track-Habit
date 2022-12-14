package com.track.trackhabit.habit.data.repository

import com.orhanobut.hawk.Hawk
import com.track.common.base.constpackage.HawkKey
import com.track.trackhabit.habit.data.local.dao.UserDao
import com.track.trackhabit.habit.domain.entity.User
import com.track.trackhabit.habit.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao,): UserRepository {
    override suspend fun addUser(user: User) {
        userDao.insertUser(user.toLocalDto())
    }

    override fun getValueIsUseAcc(): Boolean = Hawk.get(HawkKey.IS_USE_ACC, false)

    override fun logout() {
        Hawk.delete(HawkKey.ACCESS_TOKEN)
        Hawk.delete(HawkKey.REFRESH_TOKEN)
    }
}