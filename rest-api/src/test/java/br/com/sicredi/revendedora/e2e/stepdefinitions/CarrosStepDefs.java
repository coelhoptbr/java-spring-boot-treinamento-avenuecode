package br.com.sicredi.revendedora.e2e.stepdefinitions;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import br.com.sicredi.revendedora.e2e.AbstractE2ETest;
import br.com.sicredi.revendedora.e2e.stepResources.CarroStepResource;
import br.com.sicredi.revendedora.e2e.stepResources.CarroXml;
import br.com.sicredi.revendedora.model.Carro;
import br.com.sicredi.revendedora.repositories.CarroRepository;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class CarrosStepDefs extends AbstractE2ETest {

  private Map<Long, Long> actualIdsMap = new HashMap<>();

  @Autowired private CarroRepository carroRepository;

  private final String URL_CARROS = "/carros";

  private Response latestResponse;

  @Dado("que a tabela de carros esteja vazia$")
  public void que_a_tabela_de_carros_esteja_vazia() {
    carroRepository.deleteAll();
    actualIdsMap.clear();
  }

  @Entao("o cliente requisita a lista de carros$")
  public void o_cliente_requisita_a_lista_de_carros() {
    latestResponse = executeGet(URL_CARROS);
  }

  @Entao("o cliente requisita a lista de carros utilizando accept XML$")
  public void o_cliente_requisita_a_lista_de_carros_utilizando_accept_xml() {
    latestResponse = executeGetWithHeaders(URL_CARROS, xmlAcceptHeaders());
  }

  @Dado("que existem os seguintes carros no banco de dados:$")
  public void existem_os_seguintes_carros_no_banco_de_dados(List<CarroStepResource> carros) {
    for (CarroStepResource carro : carros) {
      Carro carroToCreate = carro.toCarro();
      carroToCreate.setId(null);
      Carro createdCarro = carroRepository.save(carroToCreate);
      actualIdsMap.put(carro.getId(), createdCarro.getId());
    }
  }

  @Entao("o cliente requisita a criação dos seguintes carros:$")
  public void o_cliente_requisita_a_criacao_dos_seguintes_carros(List<CarroStepResource> carros)
      throws Exception {
    for (CarroStepResource carro : carros) {
      Carro carroToCreate = carro.toCarro();
      latestResponse = executePost(URL_CARROS, carroToCreate);
      if (latestResponse.getStatusCode() == 201) {
        actualIdsMap.put(
            carro.getId(), extractIdFromLocationHeader(latestResponse.getHeader("location")));
      }
    }
  }

  @Entao("o cliente requisita a criação dos seguintes carros utilizando accept XML:$")
  public void o_cliente_requisita_a_criacao_dos_seguintes_carros_utilizando_accept_xml(
      List<CarroStepResource> carros) throws Exception {
    for (CarroStepResource carro : carros) {
      Carro carroToCreate = carro.toCarro();
      latestResponse = executePostWithHeaders(URL_CARROS, carroToCreate, xmlAcceptHeaders());
    }
  }

  @Entao("o cliente requisita a criação dos seguintes carros utilizando content-type XML:$")
  public void o_cliente_requisita_a_criacao_dos_seguintes_carros_utilizando_content_type_xml(
      List<CarroStepResource> carros) throws Exception {
    for (CarroStepResource carro : carros) {
      Carro carroToCreate = carro.toCarro();
      latestResponse =
          executePostWithHeaders(URL_CARROS, new CarroXml(carroToCreate), xmlContentTypeHeaders());
      latestResponse.print();
    }
  }

  @Entao("o cliente requisita a criação do seguinte carro com o id do carro (\\d+):")
  public void o_cliente_requisita_a_criacao_do_seguinte_carro_com_o_id_do_carro_n(
      Long id, List<CarroStepResource> carros) {
    assertEquals("Este método só pode ser chamado com 1 carro", 1, carros.size());
    Carro carroToCreate = carros.get(0).toCarro();
    carroToCreate.setId(getActualId(id));
    latestResponse = executePost(URL_CARROS, carroToCreate);
  }

  @Quando("o cliente requisita a criação de um carro sem enviar nenhum dado$")
  public void o_cliente_requistia_a_criacao_de_um_carro_sem_enviar_nenhum_dado() {
    latestResponse = executePost(URL_CARROS);
  }

  @Entao("o cliente requisita as informações do carro com ID (\\d+)$")
  public void o_cliente_requisita_as_informacoes_do_carro_com_ID_n(long id) {
    latestResponse = executeGet(URL_CARROS + "/" + getActualId(id));
  }

  @Entao("o cliente requisita as informações do carro com ID (\\d+) utilizando accept XML$")
  public void o_cliente_requisita_as_informacoes_do_carro_com_ID_n_utilizando_accept_xml(long id) {
    latestResponse = executeGetWithHeaders(URL_CARROS + "/" + getActualId(id), xmlAcceptHeaders());
  }

  @Entao("o cliente requisita a atualização do carro com ID (\\d+):$")
  public void o_cliente_requisita_a_atualizacao_do_carro_com_ID_n(
      long id, List<CarroStepResource> carros) {
    assertEquals("Este método só pode ser chamado com 1 carro", 1, carros.size());
    latestResponse = executePut(URL_CARROS + "/" + getActualId(id), carros.get(0).toCarro());
  }

  @Entao("o cliente requisita a atualização do carro com ID (\\d+) utilizando accept XML:$")
  public void o_cliente_requisita_a_atualizacao_do_carro_com_ID_n_utilizando_accept_xml(
      long id, List<CarroStepResource> carros) {
    assertEquals("Este método só pode ser chamado com 1 carro", 1, carros.size());
    latestResponse =
        executePutWithHeaders(
            URL_CARROS + "/" + getActualId(id), carros.get(0).toCarro(), xmlAcceptHeaders());
  }

  @Entao("o cliente requisita a atualização do carro com ID (\\d+) utilizando content-type XML:$")
  public void o_cliente_requisita_a_atualizacao_do_carro_com_ID_n_utilizando_content_type_xml(
      long id, List<CarroStepResource> carros) {
    assertEquals("Este método só pode ser chamado com 1 carro", 1, carros.size());
    latestResponse =
        executePutWithHeaders(
            URL_CARROS + "/" + getActualId(id),
            new CarroXml(carros.get(0).toCarro()),
            xmlContentTypeHeaders());
    latestResponse.print();
  }

  @Entao("o cliente requisita a atualização do carro com ID (\\d+) sem enviar nenhum dado$")
  public void o_cliente_requisita_a_atualizacao_do_carro_com_ID_n_sem_enviar_nenhum_dado(long id) {
    latestResponse = executePut(URL_CARROS + "/" + getActualId(id));
  }

  @Entao("o cliente requisita a exclusão do carro com ID (\\d+)$")
  public void o_cliente_requisita_a_exclusao_do_carro_com_ID_n(long id) {
    latestResponse = executeDelete(URL_CARROS + "/" + getActualId(id));
  }

  @Entao("o cliente requisita a exclusão do carro com ID (\\d+) utilizando accept XML$")
  public void o_cliente_requisita_a_exclusao_do_carro_com_ID_n_utilizando_accept_xml(long id) {
    latestResponse =
        executeDeleteWithHeaders(URL_CARROS + "/" + getActualId(id), xmlAcceptHeaders());
  }

  @E("o cliente recebe um status (\\d+)$")
  public void o_cliente_recebe_um_status_n(int expectedStatus) {
    int actualStatusCode = latestResponse.getStatusCode();
    assertEquals(
        "Status code recebido incorreto: " + latestResponse.getStatusCode(),
        expectedStatus,
        actualStatusCode);
  }

  @E("o cliente recebe o header de location$")
  public void o_cliente_recebe_o_header_de_location() throws Exception {
    String locationHeader = latestResponse.getHeader("location");
    extractIdFromLocationHeader(locationHeader);
  }

  @E("o cliente recebe o seguinte carro:$")
  public void o_cliente_recebe_o_seguinte_carro(List<CarroStepResource> carros) {
    assertEquals("Este método só pode ser chamado com 1 carro", 1, carros.size());
    Carro expectedCarro = carros.get(0).toCarro();
    Carro actualCarro = latestResponse.getBody().as(Carro.class);
    assertCarrosAreEquals(expectedCarro, actualCarro);
  }

  @E("o cliente recebe os seguintes carros:$")
  public void o_cliente_recebe_os_seguintes_carros(List<CarroStepResource> carros) {
    List<Carro> expectedCarros = carros.stream().map(CarroStepResource::toCarro).collect(toList());
    List<Carro> actualCarros = latestResponse.getBody().jsonPath().getList(".", Carro.class);
    assertListsAreEquals(expectedCarros, actualCarros);
  }

  private void assertListsAreEquals(List<Carro> expectedList, List<Carro> actualList) {
    assertEquals(expectedList.size(), actualList.size());
    for (int i = 0; i < expectedList.size(); i++) {
      Carro expected = expectedList.get(i);
      Carro actual = actualList.get(i);
      assertCarrosAreEquals(expected, actual);
    }
  }

  private void assertCarrosAreEquals(Carro expected, Carro actual) {
    assertEquals(expected.getPlaca(), actual.getPlaca());
    assertEquals(expected.getModelo().getMarca().getId(), actual.getModelo().getMarca().getId());
    assertEquals(
        expected.getModelo().getMarca().getNome(), actual.getModelo().getMarca().getNome());
    assertEquals(expected.getModelo().getId(), actual.getModelo().getId());
    assertEquals(expected.getModelo().getNome(), actual.getModelo().getNome());
  }

  private long extractIdFromLocationHeader(String locationHeader) throws Exception {
    String pattern = ("\\/carros\\/(\\d+)");
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(locationHeader);
    if (m.find()) {
      return Long.parseLong(m.group(1));
    } else {
      throw new Exception("Header de location não tem o conteúdo esperado: " + locationHeader);
    }
  }

  private long getActualId(long id) {
    return actualIdsMap.getOrDefault(id, id);
  }

  private Map<String, String> xmlAcceptHeaders() {
    Map<String, String> headers = new HashMap<>();
    headers.put("accept", MediaType.APPLICATION_XML_VALUE);
    return headers;
  }

  private Map<String, String> xmlContentTypeHeaders() {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", MediaType.APPLICATION_XML_VALUE);
    return headers;
  }
}
