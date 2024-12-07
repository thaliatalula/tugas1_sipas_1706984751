package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.*;
import apap.tugas.sipas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PasienController {
    @Autowired
    private PasienService pasienService;

    @Autowired
    private AsuransiService asuransiService;

    @Autowired
    private DiagnosisPenyakitService diagnosisPenyakitService;

    @Autowired
    private PasienDiagnosisService pasienDiagnosisService;

    @Autowired
    private PasienAsuransiService pasienAsuransiService;

    //beranda
    @RequestMapping("/")
    public String viewAllPasien(Model model){
        //ambil semua objek PasienModel yang ada
        List<PasienModel> listPasien = pasienService.getPasienList();
        //add model pasien untuk dirender
        model.addAttribute("listPasien", listPasien);
        return "beranda";
    }

    //add pasien
    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.GET)
    public String addPasienForm(Model model){
        PasienModel pasien = new PasienModel();
        EmergencyContactModel emergencyContact = new EmergencyContactModel();
        pasien.setEmergencyContact(emergencyContact);
        List<PasienAsuransiModel> listPasienAsuransi = new ArrayList<>();
        pasien.setListPasienAsuransi(listPasienAsuransi);
        PasienAsuransiModel pasienAsuransi = new PasienAsuransiModel();
        pasien.getListPasienAsuransi().add(pasienAsuransi);
        List<AsuransiModel> listAsuransi = asuransiService.getAsuransiList();

        model.addAttribute("pasien", pasien);
        model.addAttribute("emergencyContact", emergencyContact);
        model.addAttribute("listAsuransi", listAsuransi);
        return "form-add-pasien";
    }

    @RequestMapping(value = "/pasien/tambah/berhasil", method = RequestMethod.POST)
    public String submitPasien(@ModelAttribute PasienModel pasien, @ModelAttribute EmergencyContactModel emergencyContact, Model model){
        pasien.buatKode(pasien);
        for (PasienAsuransiModel pasienAsuransiModel : pasien.getListPasienAsuransi()){
            pasienAsuransiModel.setPasien(pasien);
        }
        pasienService.addPasien(pasien);
        model.addAttribute("pasien", pasien);
        return "add-pasien";
    }

    @RequestMapping(value="/pasien/tambah/berhasil", params={"tambahAsuransi"}, method = RequestMethod.POST)
    public String addRowAsuransi(PasienModel pasien, PasienAsuransiModel pasienAsuransi, BindingResult bindingResult, Model model) {
        if (pasien.getListPasienAsuransi() ==  null) {
            pasien.setListPasienAsuransi(new ArrayList<>());
        }
        pasienAsuransi.setPasien(pasien);
        pasien.getListPasienAsuransi().add(pasienAsuransi);
        List<AsuransiModel> asuransiList = asuransiService.getAsuransiList();
        model.addAttribute("pasien", pasien);
        model.addAttribute("listAsuransi", asuransiList);
        return "form-add-pasien";
    }

    @RequestMapping(value="/pasien", method = RequestMethod.GET)
    public String viewPasien(
            @RequestParam(value = "nikPasien") String nikPasien, Model model
    ){
        PasienModel pasien = pasienService.getPasienByNikPasien(nikPasien).get();
        List<PasienAsuransiModel> listAsuransi = pasien.getListPasienAsuransi();
        model.addAttribute("pasien", pasien);
        model.addAttribute("listAsuransi", listAsuransi);
        return "view-pasien";
    }

    @RequestMapping(value="/pasien/ubah/{nikPasien}", method = RequestMethod.GET)
    public String changePasienForm(@PathVariable String nikPasien, Model model){
        PasienModel existPasien = pasienService.getPasienByNikPasien(nikPasien).get();
        model.addAttribute("pasien", existPasien);
        return "form-change-pasien";
    }

    @RequestMapping(value="/pasien/ubah/berhasil", method = RequestMethod.POST)
    public String submitChangePasien(@ModelAttribute PasienModel pasienModel, Model model){
        PasienModel newPasien = pasienService.changePasien(pasienModel);
        model.addAttribute("pasien", newPasien);
        return "change-pasien";
    }

    @RequestMapping(value="/pasien/{nikPasien}/tambah-diagnosis", method = RequestMethod.GET)
    public String addPasienDiagnosisForm(@PathVariable String nikPasien, Model model){
        PasienModel pasien = pasienService.getPasienByNikPasien(nikPasien).get();
        PasienDiagnosisPenyakitModel pasienDiagnosisPenyakit = new PasienDiagnosisPenyakitModel();
        List<DiagnosisPenyakitModel> listDiagnosisPenyakit = diagnosisPenyakitService.getDiagnosisList();
        pasien.getListPasienDiagnosisPenyakit();

        model.addAttribute("pasien", pasien);
        model.addAttribute("pasienDiagnosisPenyakit", pasienDiagnosisPenyakit);
        model.addAttribute("listDiagnosisPenyakit", listDiagnosisPenyakit);

        return "form-pasien-diagnosis";
    }

    @RequestMapping(value="pasien/{nikPasien}/tambah-diagnosis", method = RequestMethod.POST)
    public String addPasienDiagnosisSubmit(@PathVariable String nikPasien, @ModelAttribute PasienDiagnosisPenyakitModel pasienDiagnosisPenyakit, Model model){
        PasienModel pasien = pasienService.getPasienByNikPasien(nikPasien).get();
        pasienDiagnosisPenyakit.setPasien(pasien);
        Date date = new Date();
        pasienDiagnosisPenyakit.setTanggalDiagnosis(date);
        pasienDiagnosisService.addPasienDiagnosis(pasienDiagnosisPenyakit);

        model.addAttribute("pasien", pasien);
        model.addAttribute("pasienDiagnosisPenyakit", pasienDiagnosisPenyakit);
        model.addAttribute("namaPenyakit1", pasienDiagnosisPenyakit.getDiagnosisPenyakit().getNamaPenyakit());

        return "pasien-diagnosis";
    }

    @RequestMapping(value="pasien/cari", method = RequestMethod.GET)
    public String cariPasien(
            @RequestParam(value = "idAsuransi") Long idAsuransi,
            @RequestParam(value = "idPenyakit") Long idPenyakit, Model model
    ){
        List<PasienModel> listPasien = new ArrayList<>();
        List<DiagnosisPenyakitModel> listDiagnosis = diagnosisPenyakitService.getDiagnosisList();
        model.addAttribute("listDiagnosis", listDiagnosis);
        List<AsuransiModel> listAsuransi = asuransiService.getAsuransiList();
        model.addAttribute("listAsuransi", listAsuransi);


        if(idAsuransi == 0 && idPenyakit == 0){
            return "cari-pasien-form";
        }

        if(idAsuransi == 0 && idPenyakit != 0){
            List<PasienDiagnosisPenyakitModel> pasienDiagnosis = pasienDiagnosisService.getDiagnosisPenyakitIdPenyakit(idPenyakit);
            for(PasienDiagnosisPenyakitModel pasien : pasienDiagnosis){
               listPasien.add(pasien.getPasien());
            }

        }
        if (idAsuransi != 0 && idPenyakit == 0){
            List<PasienAsuransiModel> pasienAsuransi = pasienAsuransiService.getAsuransiIdAsuransi(idAsuransi);
            for(PasienAsuransiModel pasien : pasienAsuransi){
                listPasien.add(pasien.getPasien());
            }
        }
        if (idAsuransi != 0 && idPenyakit != 0){
            List<PasienDiagnosisPenyakitModel> pasienDiagnosis = pasienDiagnosisService.getDiagnosisPenyakitIdPenyakit(idPenyakit);
            List<PasienAsuransiModel> pasienAsuransi = pasienAsuransiService.getAsuransiIdAsuransi(idAsuransi);
            for(PasienAsuransiModel pasien : pasienAsuransi){
                for(PasienDiagnosisPenyakitModel pasien1 : pasienDiagnosis){
                    if(pasien.equals(pasien1)){
                        listPasien.add(pasien.getPasien());
                    }
                }
            }
        }
        model.addAttribute("listPasien", listPasien);
        return "cari-pasien";
    }

    @RequestMapping(value="/pasien/cari/lakilaki-perempuan", method = RequestMethod.GET)
    public String jumlahPasien(
            @RequestParam(value = "idPenyakit") Long idPenyakit, Model model
    ){
        List<DiagnosisPenyakitModel> listDiagnosis = diagnosisPenyakitService.getDiagnosisList();
        model.addAttribute("listDiagnosis", listDiagnosis);

        if(idPenyakit == 0){
            return "form-jumlah-pasien";
        }else {
            List<PasienDiagnosisPenyakitModel> pasienLaki = pasienDiagnosisService.getPasienJenisKelaminAndDiagnosisPenyakitIdPenyakit(1, idPenyakit);
            List<PasienDiagnosisPenyakitModel> pasienPerempuan = pasienDiagnosisService.getPasienJenisKelaminAndDiagnosisPenyakitIdPenyakit(2, idPenyakit);
            Integer jumlahLaki = pasienLaki.size();
            Integer jumlahPerempuan = pasienPerempuan.size();
            DiagnosisPenyakitModel diagnosisPenyakit = diagnosisPenyakitService.getDiagnosisByIdPenyakit(idPenyakit).get();
            model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);
            model.addAttribute("jumlahLaki", jumlahLaki);
            model.addAttribute("jumlahPerempuan", jumlahPerempuan);
            return "form-tabel-jumlah-pasien";
        }
    }


}

