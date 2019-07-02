package br.com.sicredi.credito.garantiasoperacao.model;


import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CcrpTituMemoId {

    @ManyToOne
    @JoinColumn(name = "RECNO")
    private CcrpTitu ccrpTitu;

    @Column(name="COL_NAME")
    private String colName;
}
