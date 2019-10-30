package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.DiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.service.DiagnosisPenyakitService;
import apap.tugas.sipas.service.PasienDiagnosisService;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DiagnosisPenyakitController {
    @Autowired
    private DiagnosisPenyakitService diagnosisPenyakitService;

    @Autowired
    private PasienService pasienService;

    @RequestMapping(value = "/diagnosis-penyakit-all")
    public String viewAllDiagnosisPenyakit(Model model){
        List<DiagnosisPenyakitModel> listDiagnosis = diagnosisPenyakitService.getDiagnosisList();
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

    @RequestMapping(value = "/diagnosis-penyakit/hapus/{idPenyakit}", method = RequestMethod.POST)
    public String deleteDiagnosisPenyakit(@PathVariable Long idPenyakit, Model model){
        DiagnosisPenyakitModel diagnosisPenyakit = diagnosisPenyakitService.getDiagnosisByIdPenyakit(idPenyakit).get();
        List<PasienDiagnosisPenyakitModel> listPasienDiagnosis = diagnosisPenyakit.getListPasienDiagnosisPenyakit();
        if(listPasienDiagnosis.size() == 0){
            diagnosisPenyakitService.deleteDiagnosisPenyakit(diagnosisPenyakit);
            model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);
            return "delete-diagnosis-penyakit";
        }
        model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);
        return "gagal-delete-diagnosis-penyakit";

    }
}
