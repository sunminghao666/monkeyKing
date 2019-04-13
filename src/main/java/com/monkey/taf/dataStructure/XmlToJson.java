package com.monkey.taf.dataStructure;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;

/**
 * 
 * xml格式与json格式转化
 * 
 * @version 1.0
 * @since 2015-4-22 下午8:26:07
 * @author chenbaofeng
 */
public class XmlToJson {
	/**
	 * 
	 * xml格式转为json格式
	 * 
	 * @param
	 * @since 2015-8-21 上午10:10:17
	 * @author wuyuefen
	 * @return
	 */
	public static String xmlTojson(String xml) {
		StringReader input = new StringReader(xml);
		StringWriter output = new StringWriter();
		JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true)
				.autoPrimitive(true).prettyPrint(true).build();
		try {
			XMLEventReader reader = XMLInputFactory.newInstance()
					.createXMLEventReader(input);
			XMLEventWriter writer = new JsonXMLOutputFactory(config)
					.createXMLEventWriter(output);
			writer.add(reader);
			reader.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output.toString();
	}

	/**
	 * 
	 * json格式转为xml格式
	 * 
	 * @param
	 * @since 2015-8-21 上午10:14:14
	 * @author wuyuefen
	 * @return
	 */
	public static String jsonToxml(String json) {
		StringReader input = new StringReader(json);
		StringWriter output = new StringWriter();
		JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false)
				.repairingNamespaces(false).build();
		try {
			XMLEventReader reader = new JsonXMLInputFactory(config)
					.createXMLEventReader(input);
			XMLEventWriter writer = XMLOutputFactory.newInstance()
					.createXMLEventWriter(output);
			writer = new PrettyXMLEventWriter(writer);
			writer.add(reader);
			reader.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// if (output.toString().length() >= 38) {// remove <?xml version="1.0"
		// encoding="UTF-8"?>
		// return output.toString().substring(39);
		// }
		return output.toString();
	}

	public static void main(String[] args) {}
	
}
