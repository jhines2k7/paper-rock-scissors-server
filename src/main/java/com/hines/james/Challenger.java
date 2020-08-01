package com.hines.james;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Challenger {
    private String username;
    private int wins;
    private int losses;
    private int draws;
    private PlayerStatus status;
}
