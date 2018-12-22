package cz.hlubyluk.adventofcode2018.day;

import cz.hlubyluk.adventofcode2018.IDay;

public interface IDay1 extends IDay {
  String INPUT = "+16\n" + "-2\n" + "-5\n" + "+1\n" + "-12\n" + "-1\n" + "-8\n" + "+12\n" + "+5\n" + "-18\n" + "-12\n"
      + "-11\n" + "-1\n" + "+2\n" + "-8\n" + "-6\n" + "-1\n" + "-17\n" + "-18\n" + "+16\n" + "-4\n" + "+18\n" + "+7\n"
      + "-4\n" + "+13\n" + "+11\n" + "-5\n" + "+11\n" + "-8\n" + "+15\n" + "+8\n" + "-16\n" + "+15\n" + "+10\n"
      + "-19\n" + "-17\n" + "+5\n" + "-13\n" + "+10\n" + "+14\n" + "+16\n" + "+8\n" + "+14\n" + "-8\n" + "+19\n"
      + "-17\n" + "+14\n" + "+12\n" + "+8\n" + "+9\n" + "-11\n" + "+1\n" + "+2\n" + "-16\n" + "+19\n" + "+13\n" + "-5\n"
      + "+6\n" + "-10\n" + "-6\n" + "+4\n" + "-15\n" + "+20\n" + "+18\n" + "-14\n" + "+15\n" + "+3\n" + "+4\n" + "-3\n"
      + "-9\n" + "+1\n" + "+18\n" + "+8\n" + "+2\n" + "-11\n" + "+19\n" + "-3\n" + "-10\n" + "+14\n" + "+12\n" + "+6\n"
      + "-17\n" + "-14\n" + "-10\n" + "-18\n" + "+19\n" + "+4\n" + "+12\n" + "-18\n" + "-4\n" + "-8\n" + "-4\n"
      + "-20\n" + "-16\n" + "-1\n" + "-18\n" + "+2\n" + "-17\n" + "-17\n" + "+7\n" + "-12\n" + "-3\n" + "+4\n" + "+9\n"
      + "+6\n" + "+9\n" + "+6\n" + "+1\n" + "-17\n" + "+13\n" + "+14\n" + "-2\n" + "+8\n" + "+9\n" + "-12\n" + "+13\n"
      + "-5\n" + "-15\n" + "+21\n" + "+5\n" + "+18\n" + "-8\n" + "-9\n" + "+11\n" + "+14\n" + "+17\n" + "+4\n" + "-18\n"
      + "+23\n" + "+20\n" + "-14\n" + "+5\n" + "-12\n" + "+2\n" + "+25\n" + "+16\n" + "-10\n" + "-14\n" + "+17\n"
      + "-4\n" + "-1\n" + "-5\n" + "+19\n" + "+1\n" + "+17\n" + "+3\n" + "-9\n" + "+5\n" + "+8\n" + "-10\n" + "+14\n"
      + "+17\n" + "-15\n" + "+19\n" + "+1\n" + "-19\n" + "+8\n" + "+15\n" + "-12\n" + "+19\n" + "-15\n" + "+7\n"
      + "+2\n" + "-11\n" + "-18\n" + "+11\n" + "+1\n" + "+9\n" + "+18\n" + "+5\n" + "+8\n" + "-6\n" + "-16\n" + "-15\n"
      + "-9\n" + "+8\n" + "-6\n" + "-1\n" + "-18\n" + "-3\n" + "-16\n" + "+10\n" + "-2\n" + "-13\n" + "+17\n" + "+15\n"
      + "-2\n" + "-8\n" + "-11\n" + "-9\n" + "-1\n" + "+2\n" + "+13\n" + "+22\n" + "-19\n" + "+9\n" + "+8\n" + "-9\n"
      + "-20\n" + "+5\n" + "-8\n" + "-13\n" + "-5\n" + "+11\n" + "+17\n" + "+4\n" + "+13\n" + "+14\n" + "+15\n" + "+6\n"
      + "+10\n" + "-8\n" + "+7\n" + "+9\n" + "+15\n" + "+17\n" + "+15\n" + "+5\n" + "+4\n" + "+17\n" + "-16\n" + "-6\n"
      + "+7\n" + "+2\n" + "+18\n" + "+19\n" + "-3\n" + "-4\n" + "-11\n" + "+1\n" + "-15\n" + "+17\n" + "-14\n" + "+17\n"
      + "+13\n" + "-6\n" + "-8\n" + "+16\n" + "+12\n" + "+14\n" + "-3\n" + "-15\n" + "-19\n" + "-6\n" + "-9\n" + "-12\n"
      + "-18\n" + "+4\n" + "-7\n" + "+14\n" + "-10\n" + "-12\n" + "+2\n" + "+19\n" + "+12\n" + "-5\n" + "+6\n" + "+21\n"
      + "-2\n" + "-14\n" + "+21\n" + "-16\n" + "+4\n" + "+10\n" + "+10\n" + "+12\n" + "+7\n" + "+16\n" + "-1\n"
      + "+16\n" + "+5\n" + "+19\n" + "+14\n" + "+8\n" + "+14\n" + "-1\n" + "-1\n" + "-10\n" + "-13\n" + "-1\n" + "-3\n"
      + "+8\n" + "+14\n" + "-9\n" + "+8\n" + "+10\n" + "-7\n" + "+19\n" + "-13\n" + "+6\n" + "+8\n" + "+1\n" + "+11\n"
      + "-17\n" + "+10\n" + "+16\n" + "+1\n" + "-13\n" + "+16\n" + "+3\n" + "+4\n" + "-14\n" + "+16\n" + "+17\n"
      + "-14\n" + "+16\n" + "+15\n" + "+16\n" + "+19\n" + "-15\n" + "+12\n" + "+4\n" + "-21\n" + "+14\n" + "-12\n"
      + "-16\n" + "-12\n" + "-3\n" + "+11\n" + "-3\n" + "+4\n" + "-11\n" + "+3\n" + "+6\n" + "+15\n" + "-16\n" + "-16\n"
      + "-17\n" + "-17\n" + "+16\n" + "-10\n" + "+4\n" + "+10\n" + "-18\n" + "+11\n" + "-16\n" + "-16\n" + "-3\n"
      + "-12\n" + "+11\n" + "-20\n" + "+6\n" + "-11\n" + "-4\n" + "-10\n" + "-13\n" + "-19\n" + "+4\n" + "-11\n"
      + "-6\n" + "+18\n" + "-9\n" + "+12\n" + "+7\n" + "+10\n" + "-2\n" + "-3\n" + "+13\n" + "-5\n" + "-2\n" + "-2\n"
      + "-14\n" + "+7\n" + "-16\n" + "-4\n" + "-2\n" + "-6\n" + "+9\n" + "+5\n" + "-18\n" + "-15\n" + "+2\n" + "+8\n"
      + "-21\n" + "+20\n" + "-18\n" + "-10\n" + "+9\n" + "+17\n" + "-12\n" + "-8\n" + "-15\n" + "+31\n" + "-7\n"
      + "-4\n" + "-12\n" + "-25\n" + "-20\n" + "-16\n" + "-6\n" + "+18\n" + "+18\n" + "-1\n" + "+8\n" + "+5\n" + "-7\n"
      + "-30\n" + "-13\n" + "-10\n" + "-15\n" + "-12\n" + "-6\n" + "+10\n" + "+16\n" + "-5\n" + "-17\n" + "-14\n"
      + "+15\n" + "+10\n" + "+14\n" + "-2\n" + "+10\n" + "+4\n" + "+3\n" + "-19\n" + "+9\n" + "+2\n" + "-17\n" + "+9\n"
      + "-6\n" + "+1\n" + "+7\n" + "+9\n" + "+7\n" + "-4\n" + "+5\n" + "+6\n" + "+17\n" + "-4\n" + "-18\n" + "+7\n"
      + "-20\n" + "-11\n" + "-13\n" + "-9\n" + "-8\n" + "+46\n" + "-2\n" + "+22\n" + "+16\n" + "-22\n" + "-38\n"
      + "-3\n" + "+18\n" + "+28\n" + "+33\n" + "+24\n" + "+17\n" + "+11\n" + "-13\n" + "-5\n" + "+19\n" + "-9\n"
      + "+10\n" + "+15\n" + "+22\n" + "+8\n" + "+6\n" + "-13\n" + "-9\n" + "-4\n" + "-2\n" + "+20\n" + "-11\n" + "-28\n"
      + "+14\n" + "+16\n" + "+13\n" + "+5\n" + "+12\n" + "+7\n" + "+15\n" + "+19\n" + "-6\n" + "+14\n" + "+15\n"
      + "-14\n" + "+18\n" + "+17\n" + "+4\n" + "-10\n" + "+2\n" + "-10\n" + "-14\n" + "+3\n" + "-7\n" + "+22\n" + "-2\n"
      + "+1\n" + "+4\n" + "-6\n" + "-3\n" + "+14\n" + "+23\n" + "-6\n" + "-7\n" + "-16\n" + "-1\n" + "+6\n" + "+21\n"
      + "+17\n" + "+4\n" + "-17\n" + "+8\n" + "-4\n" + "+21\n" + "-18\n" + "-5\n" + "+4\n" + "+13\n" + "+24\n" + "+17\n"
      + "+13\n" + "+20\n" + "+18\n" + "+17\n" + "+9\n" + "+9\n" + "+3\n" + "+20\n" + "+17\n" + "-7\n" + "+10\n"
      + "+22\n" + "-27\n" + "+4\n" + "-15\n" + "+5\n" + "-4\n" + "-24\n" + "+4\n" + "-20\n" + "-35\n" + "+1\n" + "-5\n"
      + "+17\n" + "-20\n" + "+4\n" + "+19\n" + "+23\n" + "-6\n" + "+62\n" + "+59\n" + "-8\n" + "-10\n" + "+90\n"
      + "+99\n" + "-18\n" + "-18\n" + "+34\n" + "-6\n" + "+28\n" + "-2\n" + "+27\n" + "-5\n" + "+4\n" + "+9\n" + "+9\n"
      + "-3\n" + "+21\n" + "+4\n" + "-15\n" + "+14\n" + "-15\n" + "+22\n" + "-15\n" + "-20\n" + "+30\n" + "+9\n"
      + "-1\n" + "+107\n" + "-7\n" + "-8\n" + "-8\n" + "+28\n" + "+12\n" + "-5\n" + "-114\n" + "+3\n" + "+72\n"
      + "-14\n" + "-68\n" + "-42\n" + "+684\n" + "+82290\n" + "-12\n" + "-19\n" + "-3\n" + "+6\n" + "+4\n" + "+3\n"
      + "+16\n" + "+6\n" + "-15\n" + "+18\n" + "+3\n" + "+13\n" + "+10\n" + "-4\n" + "+10\n" + "-17\n" + "-7\n"
      + "-18\n" + "+2\n" + "+18\n" + "+13\n" + "+13\n" + "+6\n" + "+8\n" + "+18\n" + "+11\n" + "-13\n" + "+8\n" + "-7\n"
      + "+11\n" + "+4\n" + "+5\n" + "+7\n" + "-2\n" + "-16\n" + "-19\n" + "+14\n" + "+19\n" + "-6\n" + "+11\n" + "+8\n"
      + "+12\n" + "+7\n" + "+11\n" + "+5\n" + "-18\n" + "-4\n" + "+1\n" + "-7\n" + "+17\n" + "-8\n" + "-8\n" + "-5\n"
      + "-10\n" + "-16\n" + "+3\n" + "+15\n" + "-17\n" + "-9\n" + "+14\n" + "-9\n" + "-12\n" + "-4\n" + "-15\n" + "+7\n"
      + "+3\n" + "+17\n" + "-3\n" + "-19\n" + "+3\n" + "+6\n" + "-2\n" + "-17\n" + "+18\n" + "+4\n" + "-8\n" + "-8\n"
      + "-12\n" + "+10\n" + "-18\n" + "-20\n" + "-5\n" + "+15\n" + "-13\n" + "+20\n" + "+4\n" + "+9\n" + "+1\n" + "-7\n"
      + "-6\n" + "-5\n" + "+10\n" + "-9\n" + "-2\n" + "-21\n" + "+14\n" + "+21\n" + "+11\n" + "+4\n" + "-14\n" + "-5\n"
      + "-16\n" + "+1\n" + "-15\n" + "-10\n" + "+5\n" + "-1\n" + "+13\n" + "-11\n" + "-16\n" + "-16\n" + "-15\n"
      + "+13\n" + "-2\n" + "+5\n" + "-14\n" + "+4\n" + "-8\n" + "-3\n" + "+10\n" + "+5\n" + "-3\n" + "-16\n" + "+5\n"
      + "+16\n" + "-20\n" + "+11\n" + "+16\n" + "+6\n" + "-2\n" + "+6\n" + "+3\n" + "+21\n" + "-15\n" + "+1\n" + "-9\n"
      + "-2\n" + "+12\n" + "-3\n" + "-19\n" + "-9\n" + "-16\n" + "+11\n" + "+4\n" + "+18\n" + "+9\n" + "+26\n" + "-28\n"
      + "-12\n" + "-22\n" + "-18\n" + "+4\n" + "-7\n" + "-2\n" + "-5\n" + "-16\n" + "-19\n" + "-14\n" + "-3\n" + "-5\n"
      + "+14\n" + "+17\n" + "-4\n" + "+7\n" + "+4\n" + "-9\n" + "+14\n" + "+3\n" + "-11\n" + "+15\n" + "+10\n" + "+13\n"
      + "-10\n" + "-15\n" + "+18\n" + "-13\n" + "+1\n" + "+2\n" + "+3\n" + "+6\n" + "+10\n" + "+1\n" + "-18\n" + "-9\n"
      + "+3\n" + "-22\n" + "-13\n" + "-16\n" + "-15\n" + "+3\n" + "+6\n" + "+1\n" + "-12\n" + "+3\n" + "-17\n" + "-1\n"
      + "-16\n" + "+4\n" + "+9\n" + "+8\n" + "-18\n" + "-15\n" + "-18\n" + "-8\n" + "-13\n" + "+2\n" + "+15\n" + "+8\n"
      + "+3\n" + "-16\n" + "+2\n" + "-16\n" + "-12\n" + "-5\n" + "-3\n" + "-12\n" + "+19\n" + "+14\n" + "+20\n"
      + "-12\n" + "-11\n" + "+9\n" + "-10\n" + "-6\n" + "-10\n" + "+14\n" + "-17\n" + "+12\n" + "-14\n" + "+16\n"
      + "-6\n" + "+8\n" + "-10\n" + "-17\n" + "+4\n" + "-6\n" + "-1\n" + "-18\n" + "+2\n" + "+4\n" + "+9\n" + "-5\n"
      + "-13\n" + "-7\n" + "-16\n" + "-14\n" + "-8\n" + "-10\n" + "-4\n" + "-19\n" + "-19\n" + "+20\n" + "-4\n"
      + "+13\n" + "+19\n" + "+9\n" + "-17\n" + "-4\n" + "+11\n" + "+18\n" + "-1\n" + "-3\n" + "-16\n" + "-14\n"
      + "-17\n" + "-11\n" + "-6\n" + "+14\n" + "+6\n" + "-7\n" + "+5\n" + "-7\n" + "+14\n" + "+6\n" + "-3\n" + "-19\n"
      + "-17\n" + "-15\n" + "-12\n" + "+4\n" + "+5\n" + "+7\n" + "+5\n" + "+1\n" + "-8\n" + "+3\n" + "-11\n" + "-13\n"
      + "+4\n" + "-17\n" + "+15\n" + "+3\n" + "-11\n" + "-17\n" + "-4\n" + "-7\n" + "-1\n" + "-5\n" + "-7\n" + "+3\n"
      + "+5\n" + "+15\n" + "+21\n" + "+4\n" + "+17\n" + "+2\n" + "+12\n" + "-1\n" + "-1\n" + "+5\n" + "+17\n" + "-8\n"
      + "-3\n" + "-2\n" + "+7\n" + "-13\n" + "-19\n" + "-2\n" + "+3\n" + "-16\n" + "+11\n" + "+7\n" + "-14\n" + "+19\n"
      + "+3\n" + "+11\n" + "+14\n" + "-12\n" + "+2\n" + "-14\n" + "-18\n" + "+1\n" + "-27\n" + "+13\n" + "+3\n" + "+8\n"
      + "-14\n" + "-40\n" + "-1\n" + "-10\n" + "-4\n" + "+8\n" + "-31\n" + "-10\n" + "-3\n" + "+20\n" + "+6\n" + "-64\n"
      + "+7\n" + "+21\n" + "+52\n" + "+16\n" + "+32\n" + "-7\n" + "-3\n" + "+2\n" + "-91\n" + "+24\n" + "-94\n"
      + "+35\n" + "-117\n" + "+18\n" + "-19\n" + "+8\n" + "-12\n" + "+19\n" + "-12\n" + "+8\n" + "-24\n" + "-19\n"
      + "+14\n" + "-24\n" + "+7\n" + "-11\n" + "-19\n" + "-2\n" + "-16\n" + "-2\n" + "-6\n" + "+10\n" + "-12\n" + "+1\n"
      + "+5\n" + "-20\n" + "+10\n" + "-15\n" + "-3\n" + "+7\n" + "+12\n" + "+8\n" + "+11\n" + "+13\n" + "-11\n"
      + "-11\n" + "+13\n" + "+20\n" + "-18\n" + "+9\n" + "-16\n" + "+18\n" + "-10\n" + "+20\n" + "-13\n" + "+15\n"
      + "-18\n" + "+8\n" + "+14\n" + "+18\n" + "+4\n" + "-6\n" + "+16\n" + "-2\n" + "+9\n" + "-2\n" + "+33\n" + "+4\n"
      + "+6\n" + "+19\n" + "-11\n" + "+14\n" + "-13\n" + "+4\n" + "-2\n" + "-4\n" + "-21\n" + "-10\n" + "-82696";
}
