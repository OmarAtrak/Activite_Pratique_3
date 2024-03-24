package ma.hospital.application.controller;


import jakarta.validation.Valid;
import ma.hospital.application.entities.Patient;
import ma.hospital.application.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("api/patient/")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("index")
    public String index(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword
    )
    {
        Page<Patient> pagePatients = this.patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "index_patients";
    }

    @GetMapping("delete")
    public String delete(Long id, String keyword, int page) {
        this.patientRepository.deleteById(id);
        return "redirect:/api/patient/index?page=" + page + "&keyword="+keyword;
    }

    @GetMapping("page_add")
    public String page_add(Model model) {
        model.addAttribute("patient", new Patient());
        return "add_patient";
    }

    @PostMapping("save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_patient";
        }
        this.patientRepository.save(patient);
        return "redirect:/api/patient/index";
    }

    @GetMapping("edit")
    public String edit(Model model, Long id) {
        Patient patient = this.patientRepository.findById(id).orElse(null);
        if (patient == null) {
            throw new RuntimeException("Patient Introuvable");
        }
        model.addAttribute("patient", patient);
        return "edit_patient";
    }
}
