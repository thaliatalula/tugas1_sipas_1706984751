package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas.sipas.service.DiagnosisPenyakitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DiagnosisPenyakitController {
    @Autowired
    private DiagnosisPenyakitService diagnosisPenyakitService;

    @RequestMapping(value = "/diagnosis-penyakit-all")
    public String viewAllDiagnosisPenyakit(Model model){
        //ambil semua objek PasienModel yang ada
        List<DiagnosisPenyakitModel> listDiagnosis = diagnosisPenyakitService.getDiagnosisList();
        //add model pasien untuk dirender
        model.addAttribute("listDiagnosis", listDiagnosis);
        return "view-all-diagnosis";
    }

    @RequestMapping(value="/diagnosis-penyakit", method = RequestMethod.GET)
    public String viewDiagnosisPenyakit(
            @RequestParam(value = "idDiagnosis") Long idPenyakit, Model model
    ){
        DiagnosisPenyakitModel diagnosisPenyakit = diagnosisPenyakitService.getDiagnosisByIdPenyakit(idPenyakit).get();
        List<PasienDiagnosisPenyakitModel> listPasienDiagnosis = diagnosisPenyakit.getListPasienDiagnosisPenyakit();

        model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);
        model.addAttribute("listPenyakit", listPasienDiagnosis);
        return "view-diagnosis-penyakit";
    }

    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.GET)
    public String addDiagnosisPenyakit(Model model){
        DiagnosisPenyakitModel diagnosisPenyakit = new DiagnosisPenyakitModel();
        model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);
        return "form-add-diagnosis";
    }

    @RequestMapping(value = "/diagnosis-penyakit/tambah/berhasil", method = RequestMethod.POST)
    public String submitDiagnosisPenyakit(@ModelAttribute DiagnosisPenyakitModel diagnosisPenyakit, Model model){
        diagnosisPenyakitService.addDiagnosisPenyakit(diagnosisPenyakit);
        model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);
        return "add-diagnosis-penyakit";
    }
}
