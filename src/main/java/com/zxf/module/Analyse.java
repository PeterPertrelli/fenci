package com.zxf.module;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.other.PartOfSpeechTagDictionary;
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
public class Analyse extends AbstractFileSrcFenci {

    private static final String NEXT_LINE = "\r\n";
    private static final String DOUBLE_LINE = "\r\n\r\n";

    public static void main(String[] args) {
        Analyse extractor = new Analyse();
        ReadSimultaneousProcessMachine.exec(extractor);
    }

    @Override
    public String prepareResultFilePath() {
        return "F:/fenci/file/test-out-analyse.txt";
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

    @Override
    public void processAndPrint(String line, String fileName, FileWriter fw) throws IOException {

        List<Term> termList = getTerms(line);

        StringBuilder wordsSb  = new StringBuilder();
        StringBuilder natureSb = new StringBuilder();
        for(int i = 0; i < termList.size(); i++){

            Term term = termList.get(i);


            if((StringUtils.getChineseLength(wordsSb.toString())+StringUtils.getChineseLength(term.word)) > 120){
                printFile(fw, wordsSb, natureSb);
            }

            wordsSb.append(term.word);
            if(term.nature.toString().equals("w")){
                natureSb.append(term.word);
            }else{
//                natureSb.append("(").append(PartOfSpeechTagDictionary.translate(term.nature.name())).append(term.nature).append(")");
//                natureSb.append(PartOfSpeechTagDictionary.translate(term.nature.name()));
                natureSb.append("(").append(PartOfSpeechTagDictionary.translate(term.nature.name())).append(")");
            }

            int len = StringUtils.getChineseLength(wordsSb.toString()) > StringUtils.getChineseLength(natureSb.toString()) ?
                    StringUtils.getChineseLength(wordsSb.toString()) : StringUtils.getChineseLength(natureSb.toString());
            len++;

            appendSpace(wordsSb, len);
            appendSpace(natureSb, len);

            if(i == (termList.size()-1)){
                printFile(fw, wordsSb, natureSb);
            }

        }
        fw.write("\r\n============================\r\n");

    }

    private void printFile(FileWriter fw, StringBuilder wordsSb, StringBuilder natureSb) throws IOException {
        wordsSb.append(NEXT_LINE);
        natureSb.append(DOUBLE_LINE);
        fw.write(wordsSb.toString());
        fw.write(natureSb.toString());
        wordsSb.delete(0,wordsSb.length());
        natureSb.delete(0,natureSb.length());
    }

    private void appendSpace(StringBuilder sb, int len) {

        int space = len - StringUtils.getChineseLength(sb.toString());

        for(int i = 0; i < space ; i++){
            sb.append(" ");
        }

    }


}