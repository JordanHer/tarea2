
package responsive.controller;

import responsive.entity.Concierto;
import responsive.service.IConciertoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConciertoController {
    
    @Autowired
    private IConciertoService conciertoService;
    
    @GetMapping("/concierto")
    public String index(Model model){
        List<Concierto> listaConcierto = conciertoService.getAllConcierto();
        model.addAttribute("titulo", "Tabla conciertos");
        model.addAttribute("conciertos", listaConcierto);
        return "conciertos";
    }
    
    @GetMapping("/conciertoN")
    public String crearConcierto(Model model){
        List<Concierto> listaConcierto = conciertoService.getAllConcierto();
        model.addAttribute("concierto", new Concierto());
        model.addAttribute("conciertos", listaConcierto);
        return "crear";
    }
    
    @PostMapping("/save")
    public String guardarConcierto(@ModelAttribute Concierto concierto){
        conciertoService.saveConcierto(concierto);
        return "redirect:/concierto";
    }
    
    @GetMapping("/editConcierto/{id}")
    public String editarConcierto(@PathVariable("id") Long idConcierto, Model model) {
        Concierto concierto = conciertoService.getConciertoById(idConcierto);
       
        model.addAttribute("concierto", concierto);
        
        return "crear";
    }

    @GetMapping("/delete/{id}")
    public String eliminarConcierto(@PathVariable("id") Long idConcierto) {
        conciertoService.delete(idConcierto);
        return "redirect:/concierto";
    }
}
