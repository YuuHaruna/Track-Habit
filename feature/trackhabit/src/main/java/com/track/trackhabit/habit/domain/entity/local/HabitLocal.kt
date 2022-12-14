package com.track.trackhabit.habit.domain.entity.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.track.common.base.dto.LocalDto
import com.track.common.base.utils.DISPLAY_DATE_FORMAT
import com.track.common.base.utils.toFormattedString
import com.track.trackhabit.habit.domain.entity.Habit
import com.track.trackhabit.habit.domain.entity.Inspection
import com.track.trackhabit.habit.domain.entity.remote.HabitDto
import java.util.*

@Entity(foreignKeys = [ForeignKey(
    entity = UserLocal::class,
    parentColumns = arrayOf("user_id"),
    childColumns = arrayOf("user_id"),
    onDelete = CASCADE
)])
data class HabitLocal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "habit_id") val habitId: Int,
    val title: String,
    val time: Long,
    val frequency: String?,
    @ColumnInfo(name = "createAt", defaultValue = "0")
    val createAt: Long,
    @ColumnInfo(name = "updateAt", defaultValue = "0")
    val updateAt: Long
) : LocalDto {
    @ColumnInfo(name = "user_id")
    lateinit var userId: String
    override fun mapToDomainModel() = Habit(
        habitId,
        title,
        time = Date().apply { time = this@HabitLocal.time },
        emptyList(),
        frequency,
        Date().apply { time = createAt },
        Date().apply { time = updateAt }
    )

    override fun mapToRemoteDto() = HabitDto(
        habitId,
        title,
        time = Date().apply { time = this@HabitLocal.time },
        emptyList(),
        frequency,
        Date().apply { time = createAt }.toFormattedString(DISPLAY_DATE_FORMAT),
        Date().apply { time = updateAt }.toFormattedString(DISPLAY_DATE_FORMAT)
    )
}