package com.dexcom.froutes

import java.time.{Duration, LocalDateTime}

class BaseRepository(time: LocalDateTime) {
  def durationFromBoot(newTime: LocalDateTime): String =
    Duration.between(time, newTime).toString
}
