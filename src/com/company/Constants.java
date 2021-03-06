package com.company;

public final class Constants {
    public static int nanoTimeDivider = 1000000;
    public static int equityHandsCount = 10000;
    public static int posXhelper = 3;
    public static int posYname = -4;
    public static int posYmoney = 12;
    public static int posYaction = 25;
    public static int boxWidth = 95;
    public static int nameHeight = 16;
    public static int moneyHeight = 14;
    public static int actionHeight = 16;
    public static int[] playersPosX = new int[]{355, 178, 15, 17, 178, 355, 528, 694, 529};
    public static int[] playersPosY = new int[]{604, 597, 545, 176, 112, 107, 112, 545, 596};
    public static int holeCard1X = 369;
    public static int holeCard1Y = 486;
    public static int holeCard2X = 385;
    public static int holeCard2Y = 497;
    public static int CardhelperX = 8;
    public static int CardhelperY = 22;
    public static int clubsRGB = -16751867;
    public static int spadesRGB = -16777216;
    public static int heartsRGB = -908544;
    public static int diamondsRGB = -16776961;
    public static int boardCardsY = 325;
    public static int[] boardCarsdX = new int[]{284, 339, 394, 449, 504};
    public static int CardWidth = 15;
    public static int CardHeight = 17;
    public static int buttonRGB = -1184068;
    public static int[] buttonPosX = new int[]{448, 269, 146, 118, 214, 365, 507, 708, 601};
    public static int[] buttonPosY = new int[]{531, 532, 495, 267, 202, 196, 196, 445, 526};
    public static int buttonPosition = 0;
    public static int potTextX = 120;
    public static int potTextY = 347;
    public static int potHeight = 13;
    public static int potWidth = 50;
    public static int playerButtonsPosY = 681;
    public static int playerButton1PosX = 159;
    public static int playerButton2PosX = 343;
    public static int playerButton3PosX = 527;
    public static int buttonWidth = 130;
    public static int buttonHeigth = 20;
    public static int cardColor = -16777216;
    public static int[] playersCardPosX = new int[]{219, 122, 152, 247, 395, 537, 653, 552};
    public static int[] playersCardPosY = new int[]{491, 432, 245, 200, 196, 197, 433, 491};
    public static int cardRGB = -1; //d c h s
    public static int actionButY[] = new int[]{662, 690};
    public static int actionButX[] = new int[]{750, 710, 408, 221, 785};
    public static int visibilityCheckX[] = new int[]{60, 462, 710, 708,   80, 491, 706};
    public static int visibilityCheckY[] = new int[]{88, 120, 113, 364,   638, 640, 617};
    public static int visibilityCheckRGB[] = new int[]{-14144459, -11249050, -13289663, -14606047,  -14211027, -14079440, -14342610};
    public static String[] cards = new String[]{"2d", "2c", "2h", "2s", "3d", "3c", "3h", "3s", "4d", "4c", "4h", "4s", "5d", "5c", "5h", "5s", "6d", "6c", "6h", "6s", "7d", "7c", "7h", "7s",
            "8d", "8c", "8h", "8s", "9d", "9c", "9h", "9s", "td", "tc", "th", "ts", "jd", "jc", "jh", "js", "qd", "qc", "qh", "qs", "kd", "kc", "kh", "ks", "ad", "ac", "ah", "as"};
    public static int[] cardPrimes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191,
            193, 197, 199, 211, 223, 227, 229, 233, 239};

    public static String[] allHandsName = new String[]{"2das", "2dah", "2dac", "2dad", "2dks", "2dkh", "2dkc", "2dkd", "2dqs", "2dqh", "2dqc", "2dqd", "2djs", "2djh", "2djc", "2djd", "2d10s",
            "2dth", "2dtc", "2dtd", "2d9s", "2d9h", "2d9c", "2d9d", "2d8s", "2d8h", "2d8c", "2d8d", "2d7s", "2d7h", "2d7c", "2d7d", "2d6s", "2d6h", "2d6c", "2d6d", "2d5s", "2d5h", "2d5c",
            "2d5d", "2d4s", "2d4h", "2d4c", "2d4d", "2d3s", "2d3h", "2d3c", "2d3d", "2d2s", "2d2h", "2d2c", "2cas", "2cah", "2cac", "2cad", "2cks", "2ckh", "2ckc", "2ckd", "2cqs", "2cqh",
            "2cqc", "2cqd", "2cjs", "2cjh", "2cjc", "2cjd", "2cts", "2cth", "2ctc", "2ctd", "2c9s", "2c9h", "2c9c", "2c9d", "2c8s", "2c8h", "2c8c", "2c8d", "2c7s", "2c7h", "2c7c", "2c7d"
            , "2c6s", "2c6h", "2c6c", "2c6d", "2c5s", "2c5h", "2c5c", "2c5d", "2c4s", "2c4h", "2c4c", "2c4d", "2c3s", "2c3h", "2c3c", "2c3d", "2c2s", "2c2h", "2has", "2hah", "2hac", "2had"
            , "2hks", "2hkh", "2hkc", "2hkd", "2hqs", "2hqh", "2hqc", "2hqd", "2hjs", "2hjh", "2hjc", "2hjd", "2hts", "2hth", "2htc", "2htd", "2h9s", "2h9h", "2h9c", "2h9d", "2h8s", "2h8h"
            , "2h8c", "2h8d", "2h7s", "2h7h", "2h7c", "2h7d", "2h6s", "2h6h", "2h6c", "2h6d", "2h5s", "2h5h", "2h5c", "2h5d", "2h4s", "2h4h", "2h4c", "2h4d", "2h3s", "2h3h", "2h3c", "2h3d"
            , "2h2s", "2sas", "2sah", "2sac", "2sad", "2sks", "2skh", "2skc", "2skd", "2sqs", "2sqh", "2sqc", "2sqd", "2sjs", "2sjh", "2sjc", "2sjd", "2sts", "2sth", "2stc", "2std",
            "2s9s", "2s9h", "2s9c", "2s9d", "2s8s", "2s8h", "2s8c", "2s8d", "2s7s", "2s7h", "2s7c", "2s7d", "2s6s", "2s6h", "2s6c", "2s6d", "2s5s", "2s5h", "2s5c", "2s5d", "2s4s",
            "2s4h", "2s4c", "2s4d", "2s3s", "2s3h", "2s3c", "2s3d", "3das", "3dah", "3dac", "3dad", "3dks", "3dkh", "3dkc", "3dkd", "3dqs", "3dqh", "3dqc", "3dqd", "3djs", "3djh",
            "3djc", "3djd", "3dts", "3dth", "3dtc", "3dtd", "3d9s", "3d9h", "3d9c", "3d9d", "3d8s", "3d8h", "3d8c", "3d8d", "3d7s", "3d7h", "3d7c", "3d7d", "3d6s", "3d6h", "3d6c",
            "3d6d", "3d5s", "3d5h", "3d5c", "3d5d", "3d4s", "3d4h", "3d4c", "3d4d", "3d3s", "3d3h", "3d3c", "3cas", "3cah", "3cac", "3cad", "3cks", "3ckh", "3ckc", "3ckd", "3cqs", "3cqh"
            , "3cqc", "3cqd", "3cjs", "3cjh", "3cjc", "3cjd", "3cts", "3cth", "3ctc", "3ctd", "3c9s", "3c9h", "3c9c", "3c9d", "3c8s", "3c8h", "3c8c", "3c8d", "3c7s", "3c7h", "3c7c",
            "3c7d", "3c6s", "3c6h", "3c6c", "3c6d", "3c5s", "3c5h", "3c5c", "3c5d", "3c4s", "3c4h", "3c4c", "3c4d", "3c3s", "3c3h", "3has", "3hah", "3hac", "3had", "3hks", "3hkh", "3hkc"
            , "3hkd", "3hqs", "3hqh", "3hqc", "3hqd", "3hjs", "3hjh", "3hjc", "3hjd", "3hts", "3hth", "3htc", "3htd", "3h9s", "3h9h", "3h9c", "3h9d", "3h8s", "3h8h", "3h8c", "3h8d"
            , "3h7s", "3h7h", "3h7c", "3h7d", "3h6s", "3h6h", "3h6c", "3h6d", "3h5s", "3h5h", "3h5c", "3h5d", "3h4s", "3h4h", "3h4c", "3h4d", "3h3s", "3sas", "3sah", "3sac", "3sad",
            "3sks", "3skh", "3skc", "3skd", "3sqs", "3sqh", "3sqc", "3sqd", "3sjs", "3sjh", "3sjc", "3sjd", "3sts", "3sth", "3stc", "3std", "3s9s", "3s9h", "3s9c", "3s9d", "3s8s"
            , "3s8h", "3s8c", "3s8d", "3s7s", "3s7h", "3s7c", "3s7d", "3s6s", "3s6h", "3s6c", "3s6d", "3s5s", "3s5h", "3s5c", "3s5d", "3s4s", "3s4h", "3s4c", "3s4d", "4das", "4dah"
            , "4dac", "4dad", "4dks", "4dkh", "4dkc", "4dkd", "4dqs", "4dqh", "4dqc", "4dqd", "4djs", "4djh", "4djc", "4djd", "4dts", "4dth", "4dtc", "4dtd", "4d9s", "4d9h", "4d9c"
            , "4d9d", "4d8s", "4d8h", "4d8c", "4d8d", "4d7s", "4d7h", "4d7c", "4d7d", "4d6s", "4d6h", "4d6c", "4d6d", "4d5s", "4d5h", "4d5c", "4d5d", "4d4s", "4d4h", "4d4c", "4cas"
            , "4cah", "4cac", "4cad", "4cks", "4ckh", "4ckc", "4ckd", "4cqs", "4cqh", "4cqc", "4cqd", "4cjs", "4cjh", "4cjc", "4cjd", "4cts", "4cth", "4ctc", "4ctd", "4c9s", "4c9h"
            , "4c9c", "4c9d", "4c8s", "4c8h", "4c8c", "4c8d", "4c7s", "4c7h", "4c7c", "4c7d", "4c6s", "4c6h", "4c6c", "4c6d", "4c5s", "4c5h", "4c5c", "4c5d", "4c4s", "4c4h", "4has",
            "4hah", "4hac", "4had", "4hks", "4hkh", "4hkc", "4hkd", "4hqs", "4hqh", "4hqc", "4hqd", "4hjs", "4hjh", "4hjc", "4hjd", "4hts", "4hth", "4htc", "4htd", "4h9s", "4h9h"
            , "4h9c", "4h9d", "4h8s", "4h8h", "4h8c", "4h8d", "4h7s", "4h7h", "4h7c", "4h7d", "4h6s", "4h6h", "4h6c", "4h6d", "4h5s", "4h5h", "4h5c", "4h5d", "4h4s", "4sas", "4sah",
            "4sac", "4sad", "4sks", "4skh", "4skc", "4skd", "4sqs", "4sqh", "4sqc", "4sqd", "4sjs", "4sjh", "4sjc", "4sjd", "4sts", "4sth", "4stc", "4std", "4s9s", "4s9h", "4s9c",
            "4s9d", "4s8s", "4s8h", "4s8c", "4s8d", "4s7s", "4s7h", "4s7c", "4s7d", "4s6s", "4s6h", "4s6c", "4s6d", "4s5s", "4s5h", "4s5c", "4s5d", "5das", "5dah", "5dac", "5dad", "5dks",
            "5dkh", "5dkc", "5dkd", "5dqs", "5dqh", "5dqc", "5dqd", "5djs", "5djh", "5djc", "5djd", "5dts", "5dth", "5dtc", "5dtd", "5d9s", "5d9h", "5d9c", "5d9d", "5d8s", "5d8h", "5d8c",
            "5d8d", "5d7s", "5d7h", "5d7c", "5d7d", "5d6s", "5d6h", "5d6c", "5d6d", "5d5s", "5d5h", "5d5c", "5cas", "5cah", "5cac", "5cad", "5cks", "5ckh", "5ckc", "5ckd", "5cqs", "5cqh", "5cqc",
            "5cqd", "5cjs", "5cjh", "5cjc", "5cjd", "5cts", "5cth", "5ctc", "5ctd", "5c9s", "5c9h", "5c9c", "5c9d", "5c8s", "5c8h", "5c8c", "5c8d", "5c7s", "5c7h", "5c7c", "5c7d", "5c6s",
            "5c6h", "5c6c", "5c6d", "5c5s", "5c5h", "5has", "5hah", "5hac", "5had", "5hks", "5hkh", "5hkc", "5hkd", "5hqs", "5hqh", "5hqc", "5hqd", "5hjs", "5hjh", "5hjc", "5hjd", "5hts",
            "5hth", "5htc", "5htd", "5h9s", "5h9h", "5h9c", "5h9d", "5h8s", "5h8h", "5h8c", "5h8d", "5h7s", "5h7h", "5h7c", "5h7d", "5h6s", "5h6h", "5h6c", "5h6d", "5h5s", "5sas", "5sah",
            "5sac", "5sad", "5sks", "5skh", "5skc", "5skd", "5sqs", "5sqh", "5sqc", "5sqd", "5sjs", "5sjh", "5sjc", "5sjd", "5sts", "5sth", "5stc", "5std", "5s9s", "5s9h", "5s9c", "5s9d",
            "5s8s", "5s8h", "5s8c", "5s8d", "5s7s", "5s7h", "5s7c", "5s7d", "5s6s", "5s6h", "5s6c", "5s6d", "6das", "6dah", "6dac", "6dad", "6dks", "6dkh", "6dkc", "6dkd", "6dqs", "6dqh",
            "6dqc", "6dqd", "6djs", "6djh", "6djc", "6djd", "6dts", "6dth", "6dtc", "6dtd", "6d9s", "6d9h", "6d9c", "6d9d", "6d8s", "6d8h", "6d8c", "6d8d", "6d7s", "6d7h", "6d7c", "6d7d",
            "6d6s", "6d6h", "6d6c", "6cas", "6cah", "6cac", "6cad", "6cks", "6ckh", "6ckc", "6ckd", "6cqs", "6cqh", "6cqc", "6cqd", "6cjs", "6cjh", "6cjc", "6cjd", "6cts", "6cth", "6ctc",
            "6ctd", "6c9s", "6c9h", "6c9c", "6c9d", "6c8s", "6c8h", "6c8c", "6c8d", "6c7s", "6c7h", "6c7c", "6c7d", "6c6s", "6c6h", "6has", "6hah", "6hac", "6had", "6hks", "6hkh", "6hkc",
            "6hkd", "6hqs", "6hqh", "6hqc", "6hqd", "6hjs", "6hjh", "6hjc", "6hjd", "6hts", "6hth", "6htc", "6htd", "6h9s", "6h9h", "6h9c", "6h9d", "6h8s", "6h8h", "6h8c", "6h8d", "6h7s",
            "6h7h", "6h7c", "6h7d", "6h6s", "6sas", "6sah", "6sac", "6sad", "6sks", "6skh", "6skc", "6skd", "6sqs", "6sqh", "6sqc", "6sqd", "6sjs", "6sjh", "6sjc", "6sjd", "6sts", "6sth",
            "6stc", "6std", "6s9s", "6s9h", "6s9c", "6s9d", "6s8s", "6s8h", "6s8c", "6s8d", "6s7s", "6s7h", "6s7c", "6s7d", "7das", "7dah", "7dac", "7dad", "7dks", "7dkh", "7dkc", "7dkd",
            "7dqs", "7dqh", "7dqc", "7dqd", "7djs", "7djh", "7djc", "7djd", "7dts", "7dth", "7dtc", "7dtd", "7d9s", "7d9h", "7d9c", "7d9d", "7d8s", "7d8h", "7d8c", "7d8d", "7d7s", "7d7h",
            "7d7c", "7cas", "7cah", "7cac", "7cad", "7cks", "7ckh", "7ckc", "7ckd", "7cqs", "7cqh", "7cqc", "7cqd", "7cjs", "7cjh", "7cjc", "7cjd", "7cts", "7cth", "7ctc", "7ctd", "7c9s",
            "7c9h", "7c9c", "7c9d", "7c8s", "7c8h", "7c8c", "7c8d", "7c7s", "7c7h", "7has", "7hah", "7hac", "7had", "7hks", "7hkh", "7hkc", "7hkd", "7hqs", "7hqh", "7hqc", "7hqd", "7hjs", "7hjh",
            "7hjc", "7hjd", "7hts", "7hth", "7htc", "7htd", "7h9s", "7h9h", "7h9c", "7h9d", "7h8s", "7h8h", "7h8c", "7h8d", "7h7s", "7sas", "7sah", "7sac", "7sad", "7sks", "7skh", "7skc", "7skd",
            "7sqs", "7sqh", "7sqc", "7sqd", "7sjs", "7sjh", "7sjc", "7sjd", "7sts", "7sth", "7stc", "7std", "7s9s", "7s9h", "7s9c", "7s9d", "7s8s", "7s8h", "7s8c", "7s8d", "8das", "8dah", "8dac",
            "8dad", "8dks", "8dkh", "8dkc", "8dkd", "8dqs", "8dqh", "8dqc", "8dqd", "8djs", "8djh", "8djc", "8djd", "8dts", "8dth", "8dtc", "8dtd", "8d9s", "8d9h", "8d9c", "8d9d", "8d8s", "8d8h",
            "8d8c", "8cas", "8cah", "8cac", "8cad", "8cks", "8ckh", "8ckc", "8ckd", "8cqs", "8cqh", "8cqc", "8cqd", "8cjs", "8cjh", "8cjc", "8cjd", "8cts", "8cth", "8ctc", "8ctd", "8c9s", "8c9h",
            "8c9c", "8c9d", "8c8s", "8c8h", "8has", "8hah", "8hac", "8had", "8hks", "8hkh", "8hkc", "8hkd", "8hqs", "8hqh", "8hqc", "8hqd", "8hjs", "8hjh", "8hjc", "8hjd", "8hts", "8hth", "8htc",
            "8htd", "8h9s", "8h9h", "8h9c", "8h9d", "8h8s", "8sas", "8sah", "8sac", "8sad", "8sks", "8skh", "8skc", "8skd", "8sqs", "8sqh", "8sqc", "8sqd", "8sjs", "8sjh", "8sjc", "8sjd", "8sts",
            "8sth", "8stc", "8std", "8s9s", "8s9h", "8s9c", "8s9d", "9das", "9dah", "9dac", "9dad", "9dks", "9dkh", "9dkc", "9dkd", "9dqs", "9dqh", "9dqc", "9dqd", "9djs", "9djh", "9djc", "9djd",
            "9dts", "9dth", "9dtc", "9dtd", "9d9s", "9d9h", "9d9c", "9cas", "9cah", "9cac", "9cad", "9cks", "9ckh", "9ckc", "9ckd", "9cqs", "9cqh", "9cqc", "9cqd", "9cjs", "9cjh", "9cjc", "9cjd",
            "9cts", "9cth", "9ctc", "9ctd", "9c9s", "9c9h", "9has", "9hah", "9hac", "9had", "9hks", "9hkh", "9hkc", "9hkd", "9hqs", "9hqh", "9hqc", "9hqd", "9hjs", "9hjh", "9hjc", "9hjd", "9hts",
            "9hth", "9htc", "9htd", "9h9s", "9sas", "9sah", "9sac", "9sad", "9sks", "9skh", "9skc", "9skd", "9sqs", "9sqh", "9sqc", "9sqd", "9sjs", "9sjh", "9sjc", "9sjd", "9sts", "9sth", "9stc",
            "9std", "tdas", "tdah", "tdac", "tdad", "tdks", "tdkh", "tdkc", "tdkd", "tdqs", "tdqh", "tdqc", "tdqd", "tdjs", "tdjh", "tdjc", "tdjd", "tdts", "tdth", "tdtc", "tcas",
            "tcah", "tcac", "tcad", "tcks", "tckh", "tckc", "tckd", "tcqs", "tcqh", "tcqc", "tcqd", "tcjs", "tcjh", "tcjc", "tcjd", "tcts", "tcth", "thas", "thah", "thac", "thad",
            "thks", "thkh", "thkc", "thkd", "thqs", "thqh", "thqc", "thqd", "thjs", "thjh", "thjc", "thjd", "thts", "tsas", "tsah", "tsac", "tsad", "tsks", "tskh", "tskc", "tskd",
            "tsqs", "tsqh", "tsqc", "tsqd", "tsjs", "tsjh", "tsjc", "tsjd", "jdas", "jdah", "jdac", "jdad", "jdks", "jdkh", "jdkc", "jdkd", "jdqs", "jdqh", "jdqc", "jdqd", "jdjs", "jdjh", "jdjc",
            "jcas", "jcah", "jcac", "jcad", "jcks", "jckh", "jckc", "jckd", "jcqs", "jcqh", "jcqc", "jcqd", "jcjs", "jcjh", "jhas", "jhah", "jhac", "jhad", "jhks", "jhkh", "jhkc", "jhkd", "jhqs",
            "jhqh", "jhqc", "jhqd", "jhjs", "jsas", "jsah", "jsac", "jsad", "jsks", "jskh", "jskc", "jskd", "jsqs", "jsqh", "jsqc", "jsqd", "qdas", "qdah", "qdac", "qdad", "qdks", "qdkh", "qdkc",
            "qdkd", "qdqs", "qdqh", "qdqc", "qcas", "qcah", "qcac", "qcad", "qcks", "qckh", "qckc", "qckd", "qcqs", "qcqh", "qhas", "qhah", "qhac", "qhad", "qhks", "qhkh", "qhkc", "qhkd", "qhqs",
            "qsas", "qsah", "qsac", "qsad", "qsks", "qskh", "qskc", "qskd", "kdas", "kdah", "kdac", "kdad", "kdks", "kdkh", "kdkc", "kcas", "kcah", "kcac", "kcad", "kcks", "kckh", "khas", "khah",
            "khac", "khad", "khks", "ksas", "ksah", "ksac", "ksad", "adas", "adah", "adac", "acas", "acah", "ahas",
    };

    public static int[] allHandsId = new int[]{478, 466, 458, 454, 446, 422, 398, 394, 386, 382, 362, 358, 346, 334, 326, 314, 302, 298, 278, 274, 262, 254, 226, 218, 214, 206, 202,
            194, 178, 166, 158, 146, 142, 134, 122, 118, 106, 94, 86, 82, 74, 62, 58, 46, 38, 34, 26, 22, 14, 10, 6, 717, 699, 687, 681, 669, 633, 597, 591, 579, 573, 543,
            537, 519, 501, 489, 471, 453, 447, 417, 411, 393, 381, 339, 327, 321, 309, 303, 291, 267, 249, 237, 219, 213, 201, 183, 177, 159, 141, 129, 123, 111, 93, 87, 69,
            57, 51, 39, 33, 21, 15, 1195, 1165, 1145, 1135, 1115, 1055, 995, 985, 965, 955, 905, 895, 865, 835, 815, 785, 755, 745, 695, 685, 655, 635, 565, 545, 535, 515, 505,
            485, 445, 415, 395, 365, 355, 335, 305, 295, 265, 235, 215, 205, 185, 155, 145, 115, 95, 85, 65, 55, 35, 1673, 1631, 1603, 1589, 1561, 1477, 1393, 1379, 1351, 1337,
            1267, 1253, 1211, 1169, 1141, 1099, 1057, 1043, 973, 959, 917, 889, 791, 763, 749, 721, 707, 679, 623, 581, 553, 511, 497, 469, 427, 413, 371, 329, 301, 287, 259, 217,
            203, 161, 133, 119, 91, 77, 2629, 2563, 2519, 2497, 2453, 2321, 2189, 2167, 2123, 2101, 1991, 1969, 1903, 1837, 1793, 1727, 1661, 1639, 1529, 1507, 1441, 1397, 1243, 1199,
            1177, 1133, 1111, 1067, 979, 913, 869, 803, 781, 737, 671, 649, 583, 517, 473, 451, 407, 341, 319, 253, 209, 187, 143, 3107, 3029, 2977, 2951, 2899, 2743, 2587, 2561, 2509,
            2483, 2353, 2327, 2249, 2171, 2119, 2041, 1963, 1937, 1807, 1781, 1703, 1651, 1469, 1417, 1391, 1339, 1313, 1261, 1157, 1079, 1027, 949, 923, 871, 793, 767, 689, 611, 559,
            533, 481, 403, 377, 299, 247, 221, 4063, 3961, 3893, 3859, 3791, 3587, 3383, 3349, 3281, 3247, 3077, 3043, 2941, 2839, 2771, 2669, 2567, 2533, 2363, 2329, 2227, 2159, 1921,
            1853, 1819, 1751, 1717, 1649, 1513, 1411, 1343, 1241, 1207, 1139, 1037, 1003, 901, 799, 731, 697, 629, 527, 493, 391, 323, 4541, 4427, 4351, 4313, 4237, 4009, 3781, 3743,
            3667, 3629, 3439, 3401, 3287, 3173, 3097, 2983, 2869, 2831, 2641, 2603, 2489, 2413, 2147, 2071, 2033, 1957, 1919, 1843, 1691, 1577, 1501, 1387, 1349, 1273, 1159, 1121, 1007,
            893, 817, 779, 703, 589, 551, 437, 5497, 5359, 5267, 5221, 5129, 4853, 4577, 4531, 4439, 4393, 4163, 4117, 3979, 3841, 3749, 3611, 3473, 3427, 3197, 3151, 3013, 2921, 2599,
            2507, 2461, 2369, 2323, 2231, 2047, 1909, 1817, 1679, 1633, 1541, 1403, 1357, 1219, 1081, 989, 943, 851, 713, 667, 6931, 6757, 6641, 6583, 6467, 6119, 5771, 5713, 5597, 5539,
            5249, 5191, 5017, 4843, 4727, 4553, 4379, 4321, 4031, 3973, 3799, 3683, 3277, 3161, 3103, 2987, 2929, 2813, 2581, 2407, 2291, 2117, 2059, 1943, 1769, 1711, 1537, 1363, 1247,
            1189, 1073, 899, 7409, 7223, 7099, 7037, 6913, 6541, 6169, 6107, 5983, 5921, 5611, 5549, 5363, 5177, 5053, 4867, 4681, 4619, 4309, 4247, 4061, 3937, 3503, 3379, 3317, 3193,
            3131, 3007, 2759, 2573, 2449, 2263, 2201, 2077, 1891, 1829, 1643, 1457, 1333, 1271, 1147, 8843, 8621, 8473, 8399, 8251, 7807, 7363, 7289, 7141, 7067, 6697, 6623, 6401, 6179,
            6031, 5809, 5587, 5513, 5143, 5069, 4847, 4699, 4181, 4033, 3959, 3811, 3737, 3589, 3293, 3071, 2923, 2701, 2627, 2479, 2257, 2183, 1961, 1739, 1591, 1517, 9799, 9553, 9389,
            9307, 9143, 8651, 8159, 8077, 7913, 7831, 7421, 7339, 7093, 6847, 6683, 6437, 6191, 6109, 5699, 5617, 5371, 5207, 4633, 4469, 4387, 4223, 4141, 3977, 3649, 3403, 3239, 2993,
            2911, 2747, 2501, 2419, 2173, 1927, 1763, 10277, 10019, 9847, 9761, 9589, 9073, 8557, 8471, 8299, 8213, 7783, 7697, 7439, 7181, 7009, 6751, 6493, 6407, 5977, 5891, 5633, 5461,
            4859, 4687, 4601, 4429, 4343, 4171, 3827, 3569, 3397, 3139, 3053, 2881, 2623, 2537, 2279, 2021, 11233, 10951, 10763, 10669, 10481, 9917, 9353, 9259, 9071, 8977, 8507, 8413, 8131,
            7849, 7661, 7379, 7097, 7003, 6533, 6439, 6157, 5969, 5311, 5123, 5029, 4841, 4747, 4559, 4183, 3901, 3713, 3431, 3337, 3149, 2867, 2773, 2491, 12667, 12349, 12137, 12031, 11819,
            11183, 10547, 10441, 10229, 10123, 9593, 9487, 9169, 8851, 8639, 8321, 8003, 7897, 7367, 7261, 6943, 6731, 5989, 5777, 5671, 5459, 5353, 5141, 4717, 4399, 4187, 3869, 3763, 3551,
            3233, 3127, 14101, 13747, 13511, 13393, 13157, 12449, 11741, 11623, 11387, 11269, 10679, 10561, 10207, 9853, 9617, 9263, 8909, 8791, 8201, 8083, 7729, 7493, 6667, 6431, 6313, 6077,
            5959, 5723, 5251, 4897, 4661, 4307, 4189, 3953, 3599, 14579, 14213, 13969, 13847, 13603, 12871, 12139, 12017, 11773, 11651, 11041, 10919, 10553, 10187, 9943, 9577, 9211, 9089,
            8479, 8357, 7991, 7747, 6893, 6649, 6527, 6283, 6161, 5917, 5429, 5063, 4819, 4453, 4331, 4087, 16013, 15611, 15343, 15209, 14941, 14137, 13333, 13199, 12931, 12797, 12127,
            11993, 11591, 11189, 10921, 10519, 10117, 9983, 9313, 9179, 8777, 8509, 7571, 7303, 7169, 6901, 6767, 6499, 5963, 5561, 5293, 4891, 4757, 16969, 16543, 16259, 16117, 15833,
            14981, 14129, 13987, 13703, 13561, 12851, 12709, 12283, 11857, 11573, 11147, 10721, 10579, 9869, 9727, 9301, 9017, 8023, 7739, 7597, 7313, 7171, 6887, 6319, 5893, 5609, 5183,
            17447, 17009, 16717, 16571, 16279, 15403, 14527, 14381, 14089, 13943, 13213, 13067, 12629, 12191, 11899, 11461, 11023, 10877, 10147, 10001, 9563, 9271, 8249, 7957, 7811, 7519,
            7373, 7081, 6497, 6059, 5767, 18881, 18407, 18091, 17933, 17617, 16669, 15721, 15563, 15247, 15089, 14299, 14141, 13667, 13193, 12877, 12403, 11929, 11771, 10981, 10823, 10349,
            10033, 8927, 8611, 8453, 8137, 7979, 7663, 7031, 6557, 19837, 19339, 19007, 18841, 18509, 17513, 16517, 16351, 16019, 15853, 15023, 14857, 14359, 13861, 13529, 13031, 12533,
            12367, 11537, 11371, 10873, 10541, 9379, 9047, 8881, 8549, 8383, 8051, 7387, 21271, 20737, 20381, 20203, 19847, 18779, 17711, 17533, 17177, 16999, 16109, 15931, 15397, 14863,
            14507, 13973, 13439, 13261, 12371, 12193, 11659, 11303, 10057, 9701, 9523, 9167, 8989, 8633, 23183, 22601, 22213, 22019, 21631, 20467, 19303, 19109, 18721, 18527, 17557, 17363,
            16781, 16199, 15811, 15229, 14647, 14453, 13483, 13289, 12707, 12319, 10961, 10573, 10379, 9991, 9797, 24139, 23533, 23129, 22927, 22523, 21311, 20099, 19897, 19493, 19291, 18281,
            18079, 17473, 16867, 16463, 15857, 15251, 15049, 14039, 13837, 13231, 12827, 11413, 11009, 10807, 10403, 24617, 23999, 23587, 23381, 22969, 21733, 20497, 20291, 19879, 19673, 18643,
            18437, 17819, 17201, 16789, 16171, 15553, 15347, 14317, 14111, 13493, 13081, 11639, 11227, 11021, 25573, 24931, 24503, 24289, 23861, 22577, 21293, 21079, 20651, 20437, 19367, 19153,
            18511, 17869, 17441, 16799, 16157, 15943, 14873, 14659, 14017, 13589, 12091, 11663, 26051, 25397, 24961, 24743, 24307, 22999, 21691, 21473, 21037, 20819, 19729, 19511, 18857, 18203,
            17767, 17113, 16459, 16241, 15151, 14933, 14279, 13843, 12317, 27007, 26329, 25877, 25651, 25199, 23843, 22487, 22261, 21809, 21583, 20453, 20227, 19549, 18871, 18419, 17741, 17063,
            16837, 15707, 15481, 14803, 14351, 30353, 29591, 29083, 28829, 28321, 26797, 25273, 25019, 24511, 24257, 22987, 22733, 21971, 21209, 20701, 19939, 19177, 18923, 17653, 17399, 16637,
            31309, 30523, 29999, 29737, 29213, 27641, 26069, 25807, 25283, 25021, 23711, 23449, 22663, 21877, 21353, 20567, 19781, 19519, 18209, 17947, 32743, 31921, 31373, 31099, 30551, 28907,
            27263, 26989, 26441, 26167, 24797, 24523, 23701, 22879, 22331, 21509, 20687, 20413, 19043, 33221, 32387, 31831, 31553, 30997, 29329, 27661, 27383, 26827, 26549, 25159, 24881, 24047,
            23213, 22657, 21823, 20989, 20711, 35611, 34717, 34121, 33823, 33227, 31439, 29651, 29353, 28757, 28459, 26969, 26671, 25777, 24883, 24287, 23393, 22499, 36089, 35183, 34579, 34277,
            33673, 31861, 30049, 29747, 29143, 28841, 27331, 27029, 26123, 25217, 24613, 23707, 37523, 36581, 35953, 35639, 35011, 33127, 31243, 30929, 30301, 29987, 28417, 28103, 27161, 26219,
            25591, 38957, 37979, 37327, 37001, 36349, 34393, 32437, 32111, 31459, 31133, 29503, 29177, 28199, 27221, 39913, 38911, 38243, 37909, 37241, 35237, 33233, 32899, 32231, 31897, 30227,
            29893, 28891, 41347, 40309, 39617, 39271, 38579, 36503, 34427, 34081, 33389, 33043, 31313, 30967, 42781, 41707, 40991, 40633, 39917, 37769, 35621, 35263, 34547, 34189, 32399, 43259,
            42173, 41449, 41087, 40363, 38191, 36019, 35657, 34933, 34571, 45649, 44503, 43739, 43357, 42593, 40301, 38009, 37627, 36863, 46127, 44969, 44197, 43811, 43039, 40723, 38407, 38021,
            47083, 45901, 45113, 44719, 43931, 41567, 39203, 47561, 46367, 45571, 45173, 44377, 41989, 50429, 49163, 48319, 47897, 47053, 53297, 51959, 51067, 50621, 54253, 52891, 51983, 54731,
            53357, 55687,
    };


}
