package org.zzh.formatxml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.swing.JOptionPane;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.gjt.sp.jedit.EditPlugin;
import org.gjt.sp.jedit.View;

public class FormatPlugin extends EditPlugin {
	public static final String NAME = "formatxml";
	
	public static void formatxml(View view) {
		String content = view.getTextArea().getText();
		try {
			String result = format(content);
			if (content != null) {
				view.getTextArea().setText(result);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "程序出错，可能不是标准的xml!");
		}
		
	}
	
	public static String format(String str) throws Exception {
		if (str == null || "".equals(str)) {
			return null;
		}
        SAXReader reader = new SAXReader();
        StringReader in = new StringReader(str);
        Document doc = reader.read(in);
        OutputFormat formater = OutputFormat.createPrettyPrint();
        //此处Encoding设置是xml第一行encoding="UTF-8"的值，与文件内容编码无关，而是此xml的编码，如果不设置，则默认是UTF-8
        formater.setEncoding("UTF-8");
        StringWriter out = new StringWriter();
        XMLWriter writer = new XMLWriter(out, formater);
        writer.write(doc);
 
        writer.close();
        return out.toString();
    }
	public static void main(String[] args) throws Exception {
		String a = format("<a><b></b></a>");
		System.out.println(a);
	}
	
}
