package com.zxf.module;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.zxf.file.AbstractFileSrcFenci;
import com.zxf.machine.ReadSimultaneousProcessMachine;
import com.zxf.util.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author zhuxiangfei
 * @Description:
 * @date 2018/4/11
 */
public class WordSeperate extends AbstractFileSrcFenci {

    private static final int LIMIT = 50;
    private static final int SEG = 90;

    public static void main(String[] args) {
        WordSeperate extractor = new WordSeperate();
        ReadSimultaneousProcessMachine.exec(extractor);
    }


    @Override
    public String prepareResultFilePath() {
        return "F:/fenci/file/test-out-wordSep.txt";
    }

    @Override
    public List<Term> getTerms(String line) {
        List<Term> result;
        result = HanLP.segment(line);

        return result;
    }

    @Override
    public void processAndPrint(String line, String fileName, FileWriter fw) throws IOException {

        List<Term> termList = getTerms(line);

        StringBuilder old = new StringBuilder();
        StringBuilder sentence  = new StringBuilder();
        StringBuilder combine = new StringBuilder();
        for(int i = 0; i < termList.size(); i++){
            Term term = termList.get(i);

            if(StringUtils.getChineseLength(sentence.toString())
                    +StringUtils.getChineseLength(combine.toString())
                    +StringUtils.getChineseLength(term.word)
                    >LIMIT){
                mkSentence(old, sentence, combine);
                printFile(fw, sentence);
            }

            if(StringUtils.isChineseOneWord(term.word)
                    || term.nature.toString().equals("w")){
                combine.append(term.word);
            }else{
                if(StringUtils.getChineseLength(combine.toString()) == 0){
                    sentence.append(term.word+" ");
                }else{
                    sentence.append(combine.toString()).append(" ").append(term.word+" ");
                    combine.delete(0,combine.length());
                }
            }
            old.append(term.word);



            if(i == (termList.size()-1)){
                mkSentence(old, sentence, combine);
                printFile(fw,sentence);
            }
        }
        fw.write(sentence.toString() + "\r\n");
    }

    private void mkSentence(StringBuilder old, StringBuilder sentence, StringBuilder combine) {
        sentence.append(combine.toString());
        int len = StringUtils.getChineseLength(sentence.toString());
        sentence.append(StringUtils.nSpace(SEG-len));
//        int realLen = StringUtils.getChineseLength(sentence.toString());
//        sentence.append("#");
//        sentence.append(realLen);
        sentence.append(old.toString());
        old.delete(0,old.length());
        combine.delete(0,combine.length());
    }

    private void printFile(FileWriter fw, StringBuilder sb) throws IOException {
//        fw.write(StringUtils.nSpace(20)+sb.toString() + "\r\n");
        fw.write(sb.toString() + "\r\n");
        sb.delete(0,sb.length());
    }


}