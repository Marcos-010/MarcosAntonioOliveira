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
import marcosantonio.domain.Empresa;
import marcosantonio.service.Profissaoservice;
import marcosantonio.service.Empresaservice;

@Controller
@RequestMapping("/profissoes")

public class ProfissaoController {

    @Autowired
    private Profissaoservice profissaoservice;

    @Autowired
    private Empresaservice empresaservice;

    @GetMapping("/cadastrar")
    public String cadastrar(Profissao profissao) {

        return "/profissao/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {

        model.addAttribute("profissoes", profissaoservice.buscarTodos());
        return "/profissao/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Profissao profissao, RedirectAttributes attr) {

        profissaoservice.salvar(profissao);
        attr.addFlashAttribute("success", "Profissão inserida com sucesso.");
        return "redirect:/profissoes/cadastrar";
    }

    @ModelAttribute("empresas")
    public List<Empresa> listaDeEmpresas() {

        return empresaservice.buscarTodos();
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {

        model.addAttribute("profissao", profissaoservice.buscarPorId(id));
        return "/profissao/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Profissao profissao, RedirectAttributes attr) {
        profissaoservice.editar(profissao);
        attr.addFlashAttribute("success", "Profissão alterada com sucesso.");
        return "redirect:/profissoes/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {

        if (profissaoservice.profissaoTemCliente(id)) {
            attr.addFlashAttribute("fail", "Profissão não removida. Possui profissão vinculada.");
        }

        else {
            profissaoservice.excluir(id);
            attr.addFlashAttribute("success", "Profissão excluída com sucesso.");
        }

        return "redirect:/profissoes/listar";
    }
}
