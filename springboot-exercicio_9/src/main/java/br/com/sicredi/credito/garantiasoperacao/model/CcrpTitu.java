package br.com.sicredi.credito.garantiasoperacao.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="CCRPTITU")
public class CcrpTitu {

    @Id
    private Long recNo;


    @OneToMany(mappedBy = "id.ccrpTitu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CcrpTituMemo> memos;
}
