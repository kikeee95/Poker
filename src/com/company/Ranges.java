package com.company;

import sun.nio.cs.CharsetMapping;

import javax.smartcardio.CardPermission;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public  final class Ranges {

    //AA, KK, QQ, JJ, TT, 99, AKo, AKs, AQo, AQs, AJs, KQs
    public static int[] tightPos1OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127};

    public static int[] tightPos2OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127};


    public static int[] tightPos3OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127,
            42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811};

    public static int[] tightPos4OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127,
            42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 14279, 13843, 12317, 14803, 14351, 16637};

    public static int[] tightPos5OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127,
            42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 14279, 13843, 12317, 14803, 14351, 16637, 10379, 9991, 9797, 10807, 10403, 11021,
            39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021, 35263, 36019, 40301, 43039, 37523, 36581, 35953, 38957, 37979, 37001, 39913, 38243, 37909, 40309, 39617, 39271};

    public static int[] tightPos6OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127,
            42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 14279, 13843, 12317, 14803, 14351, 16637, 10379, 9991, 9797, 10807, 10403, 11021,
            39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021, 35263, 36019, 40301, 43039, 37523, 36581, 35953, 38957, 37979, 37001, 39913, 38243, 37909, 40309, 39617, 39271,
            31099, 31831, 34717, 36089, 24743, 25877, 29591, 31309, 30929, 32437, 35237, 38579, 26989, 27661, 31439, 33673, 28103, 29503, 31897, 33389, 24523, 25159, 28459, 29143, 35011, 33127, 31243, 36349, 34393, 32111, 37241, 33233, 32899, 36503, 34427, 34081,
            30301, 29987, 28417, 31459, 31133, 29177, 32231, 30227, 29893, 33043, 31313, 30967, 21509, 22657, 24883, 26123, 32743, 31921, 31373, 33221, 32387, 31553, 35611, 34121, 33823, 35183, 34579, 34277,
            30551, 28907, 27263, 30997, 29329, 27383, 33227, 29651, 29353, 31861, 30049, 29747, 26441, 26167, 24797, 26827, 26549, 24881, 28757, 26969, 26671, 28841, 27331, 27029, 23701, 22879, 22331, 24047, 23213, 21823, 25777, 24287, 23393, 25217, 24613, 23707,
            14933, 15707, 18923, 19781, 10573, 11413, 13081, 14017, 7081, 7979, 8549, 9523, 6497, 6059, 5767, 7031, 6557, 7387, 4189, 3953, 3599, 4331, 4087, 4757, 2173, 1927, 1763, 2279, 2021, 2491, 851, 713, 667, 1073, 899, 1147,
            209, 187, 143, 247, 221, 323, 14, 10, 6, 21, 15, 35};

    public static int[] tightPos7OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127,
            42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 14279, 13843, 12317, 14803, 14351, 16637, 10379, 9991, 9797, 10807, 10403, 11021,
            39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021, 35263, 36019, 40301, 43039, 37523, 36581, 35953, 38957, 37979, 37001, 39913, 38243, 37909, 40309, 39617, 39271,
            31099, 31831, 34717, 36089, 24743, 25877, 29591, 31309, 30929, 32437, 35237, 38579, 26989, 27661, 31439, 33673, 28103, 29503, 31897, 33389, 24523, 25159, 28459, 29143, 35011, 33127, 31243, 36349, 34393, 32111, 37241, 33233, 32899, 36503, 34427, 34081,
            30301, 29987, 28417, 31459, 31133, 29177, 32231, 30227, 29893, 33043, 31313, 30967, 21509, 22657, 24883, 26123, 32743, 31921, 31373, 33221, 32387, 31553, 35611, 34121, 33823, 35183, 34579, 34277,
            30551, 28907, 27263, 30997, 29329, 27383, 33227, 29651, 29353, 31861, 30049, 29747, 26441, 26167, 24797, 26827, 26549, 24881, 28757, 26969, 26671, 28841, 27331, 27029, 23701, 22879, 22331, 24047, 23213, 21823, 25777, 24287, 23393, 25217, 24613, 23707,
            14933, 15707, 18923, 19781, 10573, 11413, 13081, 14017, 7081, 7979, 8549, 9523, 6497, 6059, 5767, 7031, 6557, 7387, 4189, 3953, 3599, 4331, 4087, 4757, 2173, 1927, 1763, 2279, 2021, 2491, 851, 713, 667, 1073, 899, 1147,
            209, 187, 143, 247, 221, 323, 14, 10, 6, 21, 15, 35, 22019, 23129, 23999, 25573, 16571, 18091, 19339, 21271, 13393, 13969, 15611, 16969, 9307, 9847, 10951, 12667, 5221, 6641, 7223, 8843,
            2497, 2977, 3961, 4541, 454, 687, 1165, 1673, 21473, 22487, 26797, 29213, 19511, 20453, 24257, 25283, 17113, 18419, 21209, 22663, 15229, 16463, 17201, 18511, 26051, 25397, 24961, 27007, 26329, 25651, 30353, 29083, 28829, 30523, 29999, 29737,
            23183, 22601, 22213, 24139, 23533, 22927, 24617, 23587, 23381, 24931, 24503, 24289, 16459, 16241, 15151, 17063, 16837, 15481, 19177, 17653, 17399, 19519, 18209, 17947, 12707, 12319, 10961, 13231, 12827, 11009, 13493, 11639, 11227, 13589, 12091, 11663,
            4307, 4819, 5561, 6319, 2419, 2623, 3149, 3763, 943, 1247, 1457, 1961};



    public static int[] tightPos8OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127,
            42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 14279, 13843, 12317, 14803, 14351, 16637, 10379, 9991, 9797, 10807, 10403, 11021,
            39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021, 35263, 36019, 40301, 43039, 37523, 36581, 35953, 38957, 37979, 37001, 39913, 38243, 37909, 40309, 39617, 39271,
            31099, 31831, 34717, 36089, 24743, 25877, 29591, 31309, 22019, 23129, 23999, 25573, 16571, 18091, 19339, 21271, 30929, 32437, 35237, 38579, 26989, 27661, 31439, 33673, 21473, 22487, 26797, 29213,
            28103, 29503, 31897, 33389, 24523, 25159, 28459, 29143, 19511, 20453, 24257, 25283, 35011, 33127, 31243, 36349, 34393, 32111, 37241, 33233, 32899, 36503, 34427, 34081, 30301, 29987, 28417, 31459, 31133, 29177, 32231, 30227, 29893, 33043, 31313, 30967,
            21509, 22657, 24883, 26123, 17113, 18419, 21209, 22663, 32743, 31921, 31373, 33221, 32387, 31553, 35611, 34121, 33823, 35183, 34579, 34277, 30551, 28907, 27263, 30997, 29329, 27383, 33227, 29651, 29353, 31861, 30049, 29747,
            26441, 26167, 24797, 26827, 26549, 24881, 28757, 26969, 26671, 28841, 27331, 27029, 23701, 22879, 22331, 24047, 23213, 21823, 25777, 24287, 23393, 25217, 24613, 23707, 14933, 15707, 18923, 19781, 10573, 11413, 13081, 14017,
            7081, 7979, 8549, 9523, 6497, 6059, 5767, 7031, 6557, 7387};

    static int[] tightPos9OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127,
            42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 14279, 13843, 12317, 14803, 14351, 16637, 10379, 9991, 9797, 10807, 10403, 11021,
            39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021, 35263, 36019, 40301, 43039, 37523, 36581, 35953, 38957, 37979, 37001, 39913, 38243, 37909, 40309, 39617, 39271,
            30929, 32437, 35237, 38579, 28103, 29503, 31897, 33389};

    public static int[] standardPos1OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 10379, 9991, 9797, 10807, 10403, 11021,
            14279, 13843, 12317, 14803, 14351, 16637, 6497, 6059, 5767, 7031, 6557, 7387};

    static int[] standardPos2OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 10379, 9991, 9797, 10807, 10403, 11021,
            14279, 13843, 12317, 14803, 14351, 16637, 6497, 6059, 5767, 7031, 6557, 7387, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811};

    static int[] standardPos3OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 10379, 9991, 9797, 10807, 10403, 11021,
            14279, 13843, 12317, 14803, 14351, 16637, 6497, 6059, 5767, 7031, 6557, 7387, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 35263, 36019, 40301, 43039,
            28103, 29503, 31897, 33389, 21509, 22657, 24883, 26123, 14933, 15707, 18923, 19781, 10573, 11413, 13081, 14017};

    static int[] standardPos4OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 10379, 9991, 9797, 10807, 10403, 11021,
            14279, 13843, 12317, 14803, 14351, 16637, 6497, 6059, 5767, 7031, 6557, 7387, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 35263, 36019, 40301, 43039,
            28103, 29503, 31897, 33389, 21509, 22657, 24883, 26123, 14933, 15707, 18923, 19781, 10573, 11413, 13081, 14017, 39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021,
            37523, 36581, 35953, 38957, 37979, 37001, 39913, 38243, 37909, 40309, 39617, 39271, 7081, 7979, 8549, 9523, 4189, 3953, 3599, 4331, 4087, 4757};

    static int[] standardPos5OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 10379, 9991, 9797, 10807, 10403, 11021,
            14279, 13843, 12317, 14803, 14351, 16637, 6497, 6059, 5767, 7031, 6557, 7387, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 35263, 36019, 40301, 43039,
            28103, 29503, 31897, 33389, 21509, 22657, 24883, 26123, 14933, 15707, 18923, 19781, 10573, 11413, 13081, 14017, 39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021,
            37523, 36581, 35953, 38957, 37979, 37001, 39913, 38243, 37909, 40309, 39617, 39271, 7081, 7979, 8549, 9523, 4189, 3953, 3599, 4331, 4087, 4757, 31099, 31831, 34717, 36089, 30929, 32437, 35237, 38579,
            32743, 31921, 31373, 33221, 32387, 31553, 35611, 34121, 33823, 35183, 34579, 34277, 4307, 4819, 5561, 6319, 2173, 1927, 1763, 2279, 2021, 2491};

    static int[] standardPos6OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 10379, 9991, 9797, 10807, 10403, 11021,
            14279, 13843, 12317, 14803, 14351, 16637, 6497, 6059, 5767, 7031, 6557, 7387, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 35263, 36019, 40301, 43039,
            28103, 29503, 31897, 33389, 21509, 22657, 24883, 26123, 14933, 15707, 18923, 19781, 10573, 11413, 13081, 14017, 39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021,
            37523, 36581, 35953, 38957, 37979, 37001, 39913, 38243, 37909, 40309, 39617, 39271, 7081, 7979, 8549, 9523, 4189, 3953, 3599, 4331, 4087, 4757, 31099, 31831, 34717, 36089, 30929, 32437, 35237, 38579,
            32743, 31921, 31373, 33221, 32387, 31553, 35611, 34121, 33823, 35183, 34579, 34277, 4307, 4819, 5561, 6319, 2173, 1927, 1763, 2279, 2021, 2491, 24743, 25877, 29591, 31309, 22019, 23129, 23999, 25573,
            16571, 18091, 19339, 21271, 26989, 27661, 31439, 33673, 21473, 22487, 26797, 29213, 24523, 25159, 28459, 29143, 19511, 20453, 24257, 25283, 17113, 18419, 21209, 22663, 13289, 14039, 15347, 16157,
            7957, 8927, 10541, 11659, 2419, 2623, 3149, 3763, 943, 1247, 1457, 1961, 35011, 33127, 31243, 36349, 34393, 32111, 37241, 33233, 32899, 36503, 34427, 34081, 30301, 29987, 28417, 31459, 31133, 29177, 32231, 30227, 29893, 33043, 31313, 30967,
            32743, 31921, 31373, 33221, 32387, 31553, 35611, 34121, 33823, 35183, 34579, 34277, 30551, 28907, 27263, 30997, 29329, 27383, 33227, 29651, 29353, 31861, 30049, 29747, 26441, 26167, 24797, 26827, 26549, 24881, 28757, 26969, 26671, 28841, 27331, 27029,
            23701, 22879, 22331, 24047, 23213, 21823, 25777, 24287, 23393, 25217, 24613, 23707, 26051, 25397, 24961, 27007, 26329, 25651, 30353, 29083, 28829, 30523, 29999, 29737, 16459, 16241, 15151, 17063, 16837, 15481, 19177, 17653, 17399, 19519, 18209, 17947,
            12707, 12319, 10961, 13231, 12827, 11009, 13493, 11639, 11227, 13589, 12091, 11663, 7811, 7519, 7373, 8453, 8137, 7663, 8881, 8383, 8051, 9167, 8989, 8633, 851, 713, 667, 1073, 899, 1147, 209, 187, 143, 247, 221, 323, 14, 10, 6, 21, 15, 35 };

    static int[] standardPos7OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 10379, 9991, 9797, 10807, 10403, 11021,
            14279, 13843, 12317, 14803, 14351, 16637, 6497, 6059, 5767, 7031, 6557, 7387, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 35263, 36019, 40301, 43039,
            28103, 29503, 31897, 33389, 21509, 22657, 24883, 26123, 14933, 15707, 18923, 19781, 10573, 11413, 13081, 14017, 39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021,
            37523, 36581, 35953, 38957, 37979, 37001, 39913, 38243, 37909, 40309, 39617, 39271, 7081, 7979, 8549, 9523, 4189, 3953, 3599, 4331, 4087, 4757, 31099, 31831, 34717, 36089, 30929, 32437, 35237, 38579,
            32743, 31921, 31373, 33221, 32387, 31553, 35611, 34121, 33823, 35183, 34579, 34277, 4307, 4819, 5561, 6319, 2173, 1927, 1763, 2279, 2021, 2491, 24743, 25877, 29591, 31309, 22019, 23129, 23999, 25573,
            16571, 18091, 19339, 21271, 26989, 27661, 31439, 33673, 21473, 22487, 26797, 29213, 24523, 25159, 28459, 29143, 19511, 20453, 24257, 25283, 17113, 18419, 21209, 22663, 13289, 14039, 15347, 16157,
            7957, 8927, 10541, 11659, 2419, 2623, 3149, 3763, 943, 1247, 1457, 1961, 35011, 33127, 31243, 36349, 34393, 32111, 37241, 33233, 32899, 36503, 34427, 34081, 30301, 29987, 28417, 31459, 31133, 29177, 32231, 30227, 29893, 33043, 31313, 30967,
            32743, 31921, 31373, 33221, 32387, 31553, 35611, 34121, 33823, 35183, 34579, 34277, 30551, 28907, 27263, 30997, 29329, 27383, 33227, 29651, 29353, 31861, 30049, 29747, 26441, 26167, 24797, 26827, 26549, 24881, 28757, 26969, 26671, 28841, 27331, 27029,
            23701, 22879, 22331, 24047, 23213, 21823, 25777, 24287, 23393, 25217, 24613, 23707, 26051, 25397, 24961, 27007, 26329, 25651, 30353, 29083, 28829, 30523, 29999, 29737, 16459, 16241, 15151, 17063, 16837, 15481, 19177, 17653, 17399, 19519, 18209, 17947,
            12707, 12319, 10961, 13231, 12827, 11009, 13493, 11639, 11227, 13589, 12091, 11663, 7811, 7519, 7373, 8453, 8137, 7663, 8881, 8383, 8051, 9167, 8989, 8633, 851, 713, 667, 1073, 899, 1147, 209, 187, 143, 247, 221, 323, 14, 10, 6, 21, 15, 35,
            13393, 13969, 15611, 16969, 9307, 9847, 10951, 12667, 5221, 6641, 7223, 8843, 2497, 2977, 3961, 4541, 454, 687, 1165, 1673, 23183, 22601, 22213, 24139, 23533, 22927, 24617, 23587, 23381, 24931, 24503, 24289,
            17447, 17009, 16717, 18881, 18407, 17933, 19837, 19007, 18841, 20737, 20381, 20203, 14101, 13747, 13511, 14579, 14213, 13847, 16013, 15343, 15209, 16543, 16259, 16117, 9799, 9553, 9389, 10277, 10019, 9761, 11233, 10763, 10669, 12349, 12137, 12031,
            5497, 5359, 5267, 6931, 6757, 6583, 7409, 7099, 7037, 8621, 8473, 8399, 2629, 2563, 2519, 3107, 3029, 2951, 4063, 3893, 3859, 4427, 4351, 4313, 478, 466, 458, 717, 699, 681, 1195, 1145, 1135, 1631, 1603, 1589,
            18857, 18203, 17767, 19549, 18871, 17741, 21971, 20701, 19939, 21877, 21353, 20567, 14647, 14453, 13483, 15251, 15049, 13837, 15553, 14317, 14111, 15943, 14873, 14659, 5251, 4897, 4661, 5429, 5063, 4453, 5963, 5293, 4891, 5893, 5609, 5183,
            2911, 2747, 2501, 3053, 2881, 2537, 3337, 2867, 2773, 3551, 3233, 3127, 15229, 16463, 17201, 18511, 5723, 6161, 6901, 7597, 2993, 3397, 3901, 4717};

    static int[] standardPos8OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 10379, 9991, 9797, 10807, 10403, 11021,
            14279, 13843, 12317, 14803, 14351, 16637, 6497, 6059, 5767, 7031, 6557, 7387, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 35263, 36019, 40301, 43039,
            28103, 29503, 31897, 33389, 21509, 22657, 24883, 26123, 14933, 15707, 18923, 19781, 10573, 11413, 13081, 14017, 39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021,
            37523, 36581, 35953, 38957, 37979, 37001, 39913, 38243, 37909, 40309, 39617, 39271, 7081, 7979, 8549, 9523, 4189, 3953, 3599, 4331, 4087, 4757, 31099, 31831, 34717, 36089, 30929, 32437, 35237, 38579,
            32743, 31921, 31373, 33221, 32387, 31553, 35611, 34121, 33823, 35183, 34579, 34277, 4307, 4819, 5561, 6319, 2173, 1927, 1763, 2279, 2021, 2491, 24743, 25877, 29591, 31309, 22019, 23129, 23999, 25573,
            16571, 18091, 19339, 21271, 13393, 13969, 15611, 16969, 9307, 9847, 10951, 12667, 5221, 6641, 7223, 8843, 2497, 2977, 3961, 4541, 454, 687, 1165, 1673, 26989, 27661, 31439, 33673, 21473, 22487, 26797, 29213,
            24523, 25159, 28459, 29143, 19511, 20453, 24257, 25283, 17113, 18419, 21209, 22663, 30301, 29987, 28417, 31459, 31133, 29177, 32231, 30227, 29893, 33043, 31313, 30967, 35011, 33127, 31243, 36349, 34393, 32111, 37241, 33233, 32899, 36503, 34427, 34081,
            30551, 28907, 27263, 30997, 29329, 27383, 33227, 29651, 29353, 31861, 30049, 29747, 26441, 26167, 24797, 26827, 26549, 24881, 28757, 26969, 26671, 28841, 27331, 27029, 23701, 22879, 22331, 24047, 23213, 21823, 25777, 24287, 23393, 25217, 24613, 23707,
            2419, 2623, 3149, 3763, 943, 1247, 1457, 1961, 851, 713, 667, 1073, 899, 1147, 209, 187, 143, 247, 221, 323, 14, 10, 6, 21, 15, 35};

    static int[] standardPos9OR = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891,
            20687, 20413, 19043, 20989, 20711, 22499, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 10379, 9991, 9797, 10807, 10403, 11021,
            14279, 13843, 12317, 14803, 14351, 16637, 6497, 6059, 5767, 7031, 6557, 7387, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811, 35639, 37327, 38911, 41347, 35263, 36019, 40301, 43039,
            28103, 29503, 31897, 33389, 21509, 22657, 24883, 26123, 14933, 15707, 18923, 19781, 10573, 11413, 13081, 14017, 39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021,
            37523, 36581, 35953, 38957, 37979, 37001, 39913, 38243, 37909, 40309, 39617, 39271, 7081, 7979, 8549, 9523, 4189, 3953, 3599, 4331, 4087, 4757, 30929, 32437, 35237, 38579, 35011, 33127, 31243, 36349, 34393, 32111, 37241, 33233, 32899, 36503, 34427, 34081,
            30301, 29987, 28417, 31459, 31133, 29177, 32231, 30227, 29893, 33043, 31313, 30967, 31099, 31831, 34717, 36089, 26989, 27661, 31439, 33673, 24523, 25159, 28459, 29143};

    public static int[] VsStandardPos1OR3B = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053};

    public static int[] VsStandardPos1ORCall = new int[]{47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297};

    public static int[] VsStandardPos2OR3B = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053};

    public static int[] VsStandardPos2ORCall = new int[]{47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127};

    public static int[] VsStandardPos3OR3B = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621,
            44719, 45571, 49163, 53297, 34547, 34189, 32399, 34933, 34571, 36863};

    public static int[] VsStandardPos3ORCall = new int[]{47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811,
            35263, 36019, 40301, 43039, 28103, 29503, 31897, 33389, 27161, 26219, 25591, 28199, 27221, 28891, 20687, 20413, 19043, 20989, 20711, 22499};

    public static int[] VsStandardPos4OR3B = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621,
            44719, 45571, 49163, 53297, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891, 9307, 9847, 10951, 12667};

    public static int[] VsStandardPos4ORCall = new int[]{47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811,
            35263, 36019, 40301, 43039, 28103, 29503, 31897, 33389, 27161, 26219, 25591, 28199, 27221, 28891, 20687, 20413, 19043, 20989, 20711, 22499, 21509, 22657, 24883, 26123, 35639, 37327, 38911, 41347};

    public static int[] VsStandardPos5OR3B = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621,
            44719, 45571, 49163, 53297, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891, 9307, 9847, 10951, 12667};

    public static int[] VsStandardPos5ORCall = new int[]{47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811,
            35263, 36019, 40301, 43039, 28103, 29503, 31897, 33389, 27161, 26219, 25591, 28199, 27221, 28891, 20687, 20413, 19043, 20989, 20711, 22499, 21509, 22657, 24883, 26123, 35639, 37327, 38911, 41347, 14933, 15707, 18923, 19781,
            10573, 11413, 13081, 14017, 14279, 13843, 12317, 14803, 14351, 16637, 10379, 9991, 9797, 10807, 10403, 11021, 6497, 6059, 5767, 7031, 6557, 7387, 4189, 3953, 3599, 4331, 4087, 4757};

    public static int[] VsStandardPos6OR3B = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621,
            44719, 45571, 49163, 53297, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891, 9307, 9847, 10951, 12667, 5221, 6641, 7223, 8843, 2497, 2977, 3961, 4541, 454, 687, 1165, 1673,
            14933, 15707, 18923, 19781};

    public static int[] VsStandardPos6ORCall = new int[]{47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811,
            35263, 36019, 40301, 43039, 28103, 29503, 31897, 33389, 27161, 26219, 25591, 28199, 27221, 28891, 20687, 20413, 19043, 20989, 20711, 22499, 21509, 22657, 24883, 26123, 35639, 37327, 38911, 41347, 14933, 15707, 18923, 19781,
            10573, 11413, 13081, 14017, 14279, 13843, 12317, 14803, 14351, 16637, 10379, 9991, 9797, 10807, 10403, 11021, 6497, 6059, 5767, 7031, 6557, 7387, 4189, 3953, 3599, 4331, 4087, 4757, 2173, 1927, 1763, 2279, 2021, 2491,
            851, 713, 667, 1073, 899, 1147, 209, 187, 143, 247, 221, 323, 14, 10, 6, 21, 15, 35, 39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021, 30929, 32437, 35237, 38579, 31099, 31831, 34717, 36089,
            26989, 27661, 31439, 33673, 24523, 25159, 28459, 29143, 7081, 7979, 8549, 9523};

    public static int[] VsStandardPos7OR3B = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621,
            44719, 45571, 49163, 53297, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891, 9307, 9847, 10951, 12667, 5221, 6641, 7223, 8843, 2497, 2977, 3961, 4541, 454, 687, 1165, 1673,
            14933, 15707, 18923, 19781};

    public static int[] VsStandardPos7ORCall = new int[]{47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811,
            35263, 36019, 40301, 43039, 28103, 29503, 31897, 33389, 27161, 26219, 25591, 28199, 27221, 28891, 20687, 20413, 19043, 20989, 20711, 22499, 21509, 22657, 24883, 26123, 35639, 37327, 38911, 41347, 14933, 15707, 18923, 19781,
            10573, 11413, 13081, 14017, 14279, 13843, 12317, 14803, 14351, 16637, 10379, 9991, 9797, 10807, 10403, 11021, 6497, 6059, 5767, 7031, 6557, 7387, 4189, 3953, 3599, 4331, 4087, 4757, 2173, 1927, 1763, 2279, 2021, 2491,
            851, 713, 667, 1073, 899, 1147, 209, 187, 143, 247, 221, 323, 14, 10, 6, 21, 15, 35, 39917, 37769, 35621, 40363, 38191, 35657, 42593, 38009, 37627, 40723, 38407, 38021, 30929, 32437, 35237, 38579, 31099, 31831, 34717, 36089,
            26989, 27661, 31439, 33673, 24523, 25159, 28459, 29143, 7081, 7979, 8549, 9523};

    public static int[] VsStandardPos8OR3B = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621,
            44719, 45571, 49163, 53297, 34547, 34189, 32399, 34933, 34571, 36863};

    public static int[] VsStandardPos8ORCall = new int[]{47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 40633, 41449, 44503, 46127, 42781, 41707, 40991, 43259, 42173, 41087, 45649, 43739, 43357, 44969, 44197, 43811,
            35263, 36019, 40301, 43039, 28103, 29503, 31897, 33389, 27161, 26219, 25591, 28199, 27221, 28891, 20687, 20413, 19043, 20989, 20711, 22499};


    public static int[] Vs3BetEarly4Bet = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053};

    public static int[] Vs3BetLate4Bet = new int[]{54253, 52891, 51983, 54731, 53357, 55687, 43931, 41567, 39203, 44377, 41989, 47053, 34547, 34189, 32399, 34933, 34571, 36863, 47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621,
            44719, 45571, 49163, 53297, 9307, 9847, 10951, 12667};

    public static int[] Vs3BetEarlyCall = new int[]{47083, 45901, 45113, 47561, 46367, 45173, 50429, 48319, 47897, 51959, 51067, 50621, 44719, 45571, 49163, 53297, 34547, 34189, 32399, 34933, 34571, 36863, 27161, 26219, 25591, 28199, 27221, 28891};

    public static int[] Vs3BetLateCall = new int[]{27161, 26219, 25591, 28199, 27221, 28891, 20687, 20413, 19043, 20989, 20711, 22499, 40633, 41449, 44503, 46127, 35263, 36019, 40301, 43039, 35639, 37327, 38911, 41347, 14933, 15707, 18923, 19781, 28103, 29503, 31897, 33389};














    // Hand kombinációk prímszámait csoportosítva legenerálja, ez segít a rangek megadásában handId-k alapján
    public static void rangeHelper(){
        int number = 0;
        int container =0;
        String first = "";
        String second = "";
        HashMap<String,ArrayList<Integer>> combos = new HashMap<String,ArrayList<Integer>>();

        for(int i= 0; i < Constants.cardPrimes.length; i++){
            for(int j = Constants.cardPrimes.length-1; j > i; j--){
                int mod1 = (i+4)/4;
                int mod2 = (j+4)/4;
                switch (mod1){
                    case 1:
                        first = "2";
                        break;
                    case 2:
                        first = "3";
                    break;
                    case 3:
                        first = "4";
                        break;
                    case 4:
                        first = "5";
                        break;
                    case 5:
                        first = "6";
                        break;
                    case 6:
                        first = "7";
                        break;
                    case 7:
                        first = "8";
                        break;
                    case 8:
                        first = "9";
                        break;
                    case 9:
                        first = "10";
                        break;
                    case 10:
                        first = "J";
                        break;
                    case 11:
                        first = "Q";
                        break;
                    case 12:
                        first = "K";
                        break;
                    case 13:
                        first = "A";
                }

                switch (mod2){
                    case 1:
                        second = "2";
                        break;
                    case 2:
                        second = "3";
                        break;
                    case 3:
                        second = "4";
                        break;
                    case 4:
                        second = "5";
                        break;
                    case 5:
                        second = "6";
                        break;
                    case 6:
                        second = "7";
                        break;
                    case 7:
                        second = "8";
                        break;
                    case 8:
                        second = "9";
                        break;
                    case 9:
                        second = "10";
                        break;
                    case 10:
                        second = "J";
                        break;
                    case 11:
                        second = "Q";
                        break;
                    case 12:
                        second = "K";
                        break;
                    case 13:
                        second = "A";
                }

                container = Constants.cardPrimes[i] * Constants.cardPrimes[j];
                String combo = "";
                if(i%4 == j%4) {
                    combo = first + second + "s";
                }else{
                     combo = first + second + "o";
                }
                combos.putIfAbsent(combo, new ArrayList<Integer>());
                combos.get(combo).add(container);
                for (Map.Entry<String, ArrayList<Integer>> asd : combos.entrySet()) {
                    System.out.println(asd.getKey() + " ; " + asd.getValue());
                }
    number++;
            }
        }
        System.out.println(combos.entrySet());
        System.out.println(number);
    }


}
