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
package ru.anr.base.sampleapp.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ru.anr.base.domain.BaseEntity;

/**
 * The Document Entity.
 *
 *
 * @author Alexey Romanchuk
 * @created Nov 27, 2014
 *
 */
@Entity
@Table(name = "document")
public class Document extends BaseEntity {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 2244981911745883307L;

    /**
     * User - owner
     */
    private User owner;

    /**
     * A document content
     */
    private String content;

    // /////////////////////////////////////////////////////////////////////////
    // /// getters/setters
    // /////////////////////////////////////////////////////////////////////////

    /**
     * @return the owner
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    public User getOwner() {

        return owner;
    }

    /**
     * @param owner
     *            the owner to set
     */
    public void setOwner(User owner) {

        this.owner = owner;
    }

    /**
     * @return the content
     */
    @Column(name = "content")
    @Lob
    @Basic(fetch = FetchType.LAZY)
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
