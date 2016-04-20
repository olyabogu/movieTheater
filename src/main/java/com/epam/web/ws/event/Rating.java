
package com.epam.web.ws.event;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rating.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="rating">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HIGH"/>
 *     &lt;enumeration value="MIDDLE"/>
 *     &lt;enumeration value="LOW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "rating", namespace = "http://epam.com/web/ws/event")
@XmlEnum
public enum Rating {

    HIGH,
    MIDDLE,
    LOW;

    public String value() {
        return name();
    }

    public static Rating fromValue(String v) {
        return valueOf(v);
    }

}
