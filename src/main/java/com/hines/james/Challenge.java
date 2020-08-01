package com.hines.james;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Challenge {
    Player initiator;
    Player target;
    boolean accepted;
}
