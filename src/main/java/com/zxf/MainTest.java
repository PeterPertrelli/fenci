package com.zxf;

import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.zxf.util.StringUtils;

/**
 * @author zhuxiangfei
 * @Description:
 * @date 2018/3/28
 */
public class MainTest {
    public static void main(String[] args) {

        int seg = 30;

        System.out.println(StringUtils.nLetter(30,'i'));
        String scentence = "sfda是打发sdf";

        StringBuilder sb = new StringBuilder("对于 “在 探路 性的 工作 中， 怎么 能 比较 快地");

        System.out.println(sb.toString()+"#"+StringUtils.getChineseLength(sb.toString()));
        sb.append(StringUtils.nSpace(seg-StringUtils.getChineseLength(sb.toString())));
        System.out.println(sb.toString()+"#"+StringUtils.getChineseLength(sb.toString()));

        System.out.println(StringUtils.getChineseLength("“"));

//
//
//        scentence = "发斯蒂芬。,";
//        System.out.println(scentence+StringUtils.nSpace(seg-StringUtils.getChineseLength(scentence))+"#");
//        scentence = ",,，，是打发很快就";
//        System.out.println(scentence+StringUtils.nSpace(seg-StringUtils.getChineseLength(scentence))+"#");



    }

}
