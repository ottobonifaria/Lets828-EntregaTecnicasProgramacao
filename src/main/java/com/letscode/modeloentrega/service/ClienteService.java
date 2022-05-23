package com.letscode.modeloentrega.service;

import com.letscode.modeloentrega.domain.Cliente;
import com.letscode.modeloentrega.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClienteService implements iClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Integer calcularIdadeCliente(Integer codigo) {

        Cliente cliente = clienteRepository.findById(codigo).get();

        /*
            URL: http://localhost:8080/v1/clientes/calcular_idade?codigo=1

            Construa a lógica de código necessária para responder quantos anos o cliente possui.

            Considere que o código acima já está retornando o cliente correto da base de dados, sendo necessário
            apenas desenvolver a lógica de cálculo para responder sua idade.

            O retorno deste método deverá ser apenas um integer que represente a idade deste cliente.
         */

        return Math.toIntExact(ChronoUnit.YEARS.between(cliente.getDataNascimento(), LocalDate.now()));
    }
    @Override
    public List<String> listarNomesClientes() {

        List<Cliente> listaClientes = clienteRepository.findAll();

        /*
            URL: http://localhost:8080/v1/clientes/listar

            Refatore o método abaixo para gerar a mesma saída, porém sem o uso de estruturas de repetição (for, while, etc.)

            Saída esperada: uma lista contendo APENAS os nomes dos clientes e com todos os seus valores em letras maiúsculas.

            Considere que a consulta acima já retorna uma lista completa com todos os clientes existentes.
         */

        /*List<String> listaNomes = new ArrayList<>();

        for (int i = 0; i < listaClientes.size(); i++) {
            listaNomes.add(listaClientes.get(i).getNome().toUpperCase());
        }
        return listaNomes;
         */

        return listaClientes.stream()
                .map(x -> x.getNome().toUpperCase(Locale.ROOT))
                .collect(Collectors.toList());
    }
    @Override
    public String  consultarNomeCliente (Integer codigo) {
        /*
            URL: http://localhost:8080/v1/clientes/consultar_nome?codigo=9999

            Refatore o código abaixo para garantir a boa prática de evitar retornos nulos ou excecoes por dados null;

            Cenário esperado: quando o código informado for válido (existir um cliente com aquele código), deverá ser retornado
            o nome do cliente encontrado. Caso contrário, devolver uma mensagem de erro avisando que não existe ninguém com o código informado.
         */
        /*Cliente cliente = clienteRepository.findById(codigo).get();
        return cliente.getNome();
         */
        Optional<String> cliente = clienteRepository.findById(codigo).stream()
                .filter(Objects::nonNull)
                .map(Cliente::getNome)
                .findFirst();
        return cliente.orElseThrow(() -> new RuntimeException("Cliente Não Cadastrado"));
    }

    @Override
    public List<Cliente> listarClientesContato() {

        List<Cliente> listaClientes = clienteRepository.findAll();

        /*
            URL: http://localhost:8080/v1/clientes/listar_mulheres

            A partir da lista de clientes obtida pelo codigo acima, devolva apenas aqueles clientes que se encaixarem no perfil abaixo:
                - gênero FEMININO (F)
                -idade maior ou igual a 30 anos
                - quantidade máxima de clientes na lista: 3
         */
        //return listaClientes;
        return listaClientes.stream()
                .filter(x -> x.getGenero().equals('F'))
                .filter(z-> (ChronoUnit.YEARS.between(z.getDataNascimento(),LocalDate.now()))>=30)
                .sorted(Comparator.comparing(Cliente::getDataNascimento))
                .limit(3)
                .collect(Collectors.toList());

    }

    @Override
    public List<Cliente> listaQuebrada() {

        /*
            URL: http://localhost:8080/v1/clientes/bug

            Este método deveria listar todos os dados dos clientes ordenados em ordem decrescente pela quantidade de visitas limitados em até 3 resultados,
            porém não é isso que está acontecendo. Verifique se as implementações abaixo estão corretas e, se necessário, faça as modificações que julgar apropriadas.
        */
        List<Cliente> listaClientes = clienteRepository.findAll();
        /*
        listaClientes.stream().skip(3)
                .sorted(Comparator.comparingInt(Cliente::getQuantidadeVisistas))
                .map(x -> { return new Cliente(listaClientes.get(0).getCodigo(), listaClientes.get(0).getNome(), listaClientes.get(0).getDataNascimento(), listaClientes.get(0).getGenero(), listaClientes.get(0).getQuantidadeVisistas()); })
                .collect(Collectors.toList());
        return listaClientes;
         */

        return listaClientes.stream()
                .sorted(Comparator.comparingInt(Cliente::getQuantidadeVisitas).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }
}
