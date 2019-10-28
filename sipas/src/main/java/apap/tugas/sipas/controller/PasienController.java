package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.model.EmergencyContactModel;
import apap.tugas.sipas.model.PasienAsuransiModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.service.AsuransiService;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PasienController {
    @Autowired
    private PasienService pasienService;

    @Autowired
    private AsuransiService asuransiService;

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

    @RequestMapping(value="/pasien/ubah/{nikPasien}", method = RequestMethod.POST)
    public String submitChangePasien(@PathVariable String nikPasien, @ModelAttribute PasienModel pasienModel, Model model){
        PasienModel newPasien = pasienService.changePasien(pasienModel);
        model.addAttribute("pasien", newPasien);
        return "change-pasien";
    }
}

