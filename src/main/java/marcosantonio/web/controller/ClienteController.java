package marcosantonio.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import marcosantonio.domain.Profissao;
import marcosantonio.domain.Cliente;
import marcosantonio.domain.UF;
import marcosantonio.service.Profissaoservice;
import marcosantonio.service.Clienteservice;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private Profissaoservice profissaoservice;

    @Autowired
    private Clienteservice clienteservice;

    @GetMapping("/cadastrar")
    public String cadastrar(Cliente cliente) {

        return "/cliente/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {

        model.addAttribute("clientes", clienteservice.buscarTodos());

        return "/cliente/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Cliente cliente, RedirectAttributes attr) {

        clienteservice.salvar(cliente);
        attr.addFlashAttribute("success", "Cliente cadastrado com sucesso.");

        return "redirect:/clientes/cadastrar";
    }

    @ModelAttribute("profissoes")
    public List<Profissao> getProfissoes() {

        return profissaoservice.buscarTodos();
    }

    @ModelAttribute("ufs")
    public UF[] getUFs() {

        return UF.values();
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {

        model.addAttribute("cliente", clienteservice.buscarPorId(id));

        return "cliente/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Cliente cliente, RedirectAttributes attr) {

        clienteservice.editar(cliente);
        attr.addFlashAttribute("success", "Cliente alterado com sucesso.");

        return "redirect:/clientes/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {

        clienteservice.excluir(id);
        attr.addFlashAttribute("success", "Cliente excluído com sucesso.");

        return "redirect:/clientes/listar";
    }
}
