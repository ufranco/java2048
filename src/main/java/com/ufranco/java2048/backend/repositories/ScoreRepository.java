package com.ufranco.java2048.backend.repositories;

import com.ufranco.java2048.backend.models.Score;

import java.util.ArrayList;
import java.util.List;

public class ScoreRepository {

  /*
  SELECT
    PLAYER,
    SCORE
  FROM
    HIGHSCORE
  ORDER BY
    SCORE DESC
  LIMIT 10;
  */

  public Score createScore(Score score) {
    return score;
  }

  public List<Score> getHighscores() {
    return new ArrayList<>();
  }



}
