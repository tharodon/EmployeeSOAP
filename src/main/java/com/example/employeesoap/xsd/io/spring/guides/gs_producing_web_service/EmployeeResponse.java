//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.03 at 06:05:50 PM MSK 
//


package io.spring.guides.gs_producing_web_service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import io.spring.guides.gs_producing_web_service.EmployeeDto;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="employeeDto" type="{http://spring.io/guides/gs-producing-web-service}employeeDto"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "employeeDto"
})
@XmlRootElement(name = "employeeResponse")
public class EmployeeResponse {

    @XmlElement(required = true)
    protected EmployeeDto employeeDto;

    /**
     * Gets the value of the employeeDto property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeDto }
     *     
     */
    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    /**
     * Sets the value of the employeeDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeDto }
     *     
     */
    public void setEmployeeDto(EmployeeDto value) {
        this.employeeDto = value;
    }

}
