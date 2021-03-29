package com.ufranco.java2048.backend.models;

public record Score(
  String player,
  Integer score,
  Integer moves
) { }
