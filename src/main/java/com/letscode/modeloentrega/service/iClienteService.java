package com.letscode.modeloentrega.service;

import com.letscode.modeloentrega.domain.Cliente;

import java.util.List;

public interface iClienteService {

    Integer calcularIdadeCliente(Integer codigo);

    List<String> listarNomesClientes();

    String consultarNomeCliente(Integer codigo);

    List<Cliente> listarClientesContato();

    List<Cliente> listaQuebrada();
}
