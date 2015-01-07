/*
 * Copyright 2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package ru.anr.base.sampleapp.services.documents;

import java.time.ZonedDateTime;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.anr.base.domain.api.models.RequestModel;

/**
 * Document representation.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 28, 2014
 *
 */
@XmlRootElement(name = "document")
public class DocumentModel extends RequestModel {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 7030558558749974076L;

    /**
     * Identifier
     */
    private Long id;

    /**
     * When it's created
     */
    private ZonedDateTime created;

    /**
     * Document's content
     */
    private String content;

    /**
     * @return the id
     */
    @XmlAttribute(name = "id")
    public Long getId() {

        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return the created
     */
    @XmlAttribute(name = "created")
    public ZonedDateTime getCreated() {

        return created;
    }

    /**
     * @param created
     *            the created to set
     */
    public void setCreated(ZonedDateTime created) {

        this.created = created;
    }

    /**
     * @return the content
     */
    @XmlElement(name = "content")
    // @XmlValue()
    public String getContent() {

        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {

        this.content = content;
    }
}
