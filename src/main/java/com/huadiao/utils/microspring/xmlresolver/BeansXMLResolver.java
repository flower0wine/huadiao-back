package com.huadiao.utils.microspring.xmlresolver;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectName 花凋
 * @author  flowerwine
 * @version  1.1
 * @description  xml 文档解析（解析 bean）
 */
public class BeansXMLResolver {
    private static final DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();

    private BeansXMLResolver(){}

    /**
     * 该方法会将 xml 中的 bean 标签转换为对象, 在 bean 标签中的 property 标签会注入指定对象到属性
     * @param document 文档对象
     * @return 返回包含 bean class 属性对应的对象的集合
     * @throws Exception 可能抛出未知异常
     */
    public static Map<String, Object> resolveXML(Document document) throws Exception {
        // 获取 beans 标签
        NodeList beans = document.getElementsByTagName("beans");
        // 因为 beans 标签限定为一个, 所以只取第一个 beans, 并获取子元素
        NodeList beanList = beans.item(0).getChildNodes();
        Map<String, Object> map = new HashMap<>();
        for(int i = 0; i < beanList.getLength(); i++) {
            Node node = beanList.item(i);
            // 如果子元素是节点元素, 并且是 bean 标签
            if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("bean")) {
                // Element 类型提供了更简单的 API
                Element bean = (Element) beanList.item(i);
                String idName = bean.getAttribute("id");
                String className = bean.getAttribute("class");
                // 反射创建对象
                Class<?> beanClass = Class.forName(className);
                Object o = beanClass.getConstructor().newInstance();
                map.put(idName, o);
            }
        }
        // 为创建的 对象设置属性, 如果有 property 标签
        for(int i = 0; i < beanList.getLength(); i++) {
            Node node = beanList.item(i);
            // 如果子元素是节点元素, 并且是 bean 标签
            if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("bean")) {
                NodeList childNodes = node.getChildNodes();
                Element beanNode = (Element) node;
                String beanId = beanNode.getAttribute("id");
                for(int j = 0; j < childNodes.getLength(); j++) {
                    Node item = childNodes.item(j);
                    // 如果子元素是节点并且节点名为 property
                    if(item.getNodeType() == Node.ELEMENT_NODE && item.getNodeName().equals("property")) {
                        Element property = (Element) item; // Element 类型 API 更方便
                        String propertyName = property.getAttribute("name");
                        String propertyRef = property.getAttribute("ref");
                        Object bean = map.get(beanId);
                        // 利用反射注入属性
                        Field field = map.get(beanId).getClass().getDeclaredField(propertyName);
                        field.setAccessible(true); // 无视访问权限
                        field.set(bean, map.get(propertyRef));
                    }
                }
            }
        }
        return map;
    }

    /**
     * 该方法会将 xml 中的 bean 标签转换为对象, 在 bean 标签中的 property 标签会注入指定对象到属性
     * @param path xml 文件路径
     * @return 返回包含 bean class 属性对应的对象的集合
     * @throws Exception 可能抛出未知异常
     */
    public static Map<String, Object> resolveXML(String path) throws Exception {
        // 使用类加载器将资源转化为流
        InputStream inputStream = BeansXMLResolver.class.getClassLoader().getResourceAsStream(path);
        DocumentBuilder documentBuilder = DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream); // 解析

        // 返回处理好的集合
        return resolveXML(document);
    }

    /**
     * 该方法会将 xml 中的 bean 标签转换为对象, 在 bean 标签中的 property 标签会注入指定对象到属性
     * @param file File 文件对象
     * @return 返回包含 bean class 属性对应的对象的集合
     * @throws Exception 可能抛出未知异常
     */
    public static Map<String, Object> resolveXML(File file) throws Exception {
        // 使用类加载器将资源转化为流
        DocumentBuilder documentBuilder = DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();
        Document document = documentBuilder.parse(file); // 解析

        return resolveXML(document);
    }
}
