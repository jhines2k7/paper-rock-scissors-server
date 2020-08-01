package com.hines.james;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Player {
    private String id;
    private Shape shape;
    private String gameId;
    private int wins;
    private int losses;
    private int draws;
}
