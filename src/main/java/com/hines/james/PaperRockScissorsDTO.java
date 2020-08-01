package com.hines.james;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperRockScissorsDTO {
    int playerId;
    int oppId;
    Shape p1Shape;
    Shape p2Shape;
    String result;
}
