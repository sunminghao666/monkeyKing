package com.monkey.taf.dataStructure;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

/**
 * 
 * xml格式与json格式转化
 * 
 * @version 1.0
 * @since 2015-4-22 下午8:26:07
 * @author chenbaofeng
 */
public class XmlToJson {

	public static String xml2JSON(String xml) {
		return new XMLSerializer().read(xml).toString();
	}

	public static String json2XML(String json) {
		try {
			XMLSerializer serializer = new XMLSerializer();
			JSON jsonObject = JSONSerializer.toJSON(json);
			return serializer.write(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
