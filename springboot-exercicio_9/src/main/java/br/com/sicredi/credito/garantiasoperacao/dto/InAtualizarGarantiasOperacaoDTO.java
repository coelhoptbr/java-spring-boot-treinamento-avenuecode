package br.com.sicredi.credito.garantiasoperacao.dto;


import java.math.BigDecimal;
import java.util.Date;

public class InAtualizarGarantiasOperacaoDTO {

    private String codigoTitulo;

    private String codigoGarantia;

    private BigDecimal valorAvaliacao;

    private BigDecimal valorGarantia;

    private BigDecimal valorOriginal;

    private String codigoBem;

    private String proprietarioBem;

    private boolean naoAssociado;

    private Short grauGarantia;

    private Date dataRegistroAlienacao;

    private Long oidContratoHipotecaGC;

    private Long oidContratoAlienacaoGC;

    private Date dataUltimaReavaliacao;

    private String descritivoGarantiasOferecidas;
    // gravar na ccrptitu_memo com col_name = FMGARANTIA e o RECNO igual ao RECNO do titulo da CCRPTITU

//SELECT * FROM CRED_OWNER.TITULO_CREDITO WHERE OID_INSTANCIA_ANALISE IS NOT NULL;
}
