package br.com.sicredi.credito.garantiasoperacao.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CCRPTITU_MEMO")
public class CcrpTituMemo {

    @EmbeddedId
    private CcrpTituMemoId id;

    private byte[] memo;

}
