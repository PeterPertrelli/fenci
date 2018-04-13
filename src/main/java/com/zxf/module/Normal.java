package com.zxf.module;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.other.PartOfSpeechTagDictionary;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.zxf.file.AbstractFileSrcFenci;
import com.zxf.machine.ReadSimultaneousProcessMachine;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author zhuxiangfei
 * @Description:
 * @date 2018/4/11
 */
public class Normal extends AbstractFileSrcFenci {

    public static void main(String[] args) {
        Normal extractor = new Normal();
        ReadSimultaneousProcessMachine.exec(extractor);
    }

    @Override
    public List<Term> getTerms(String line) {
        List<Term> result;

//        result = HanLP.segment(line);
//        result = StandardTokenizer.segment(line);
//        result = NLPTokenizer.segment(line);
//        result = IndexTokenizer.segment(line);

//        Segment segment = new CRFSegment();

        result = HanLP.segment(line);

        return result;
    }

}
