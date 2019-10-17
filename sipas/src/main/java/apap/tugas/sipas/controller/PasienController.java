package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.service.AsuransiService;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PasienController {
    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("asuransiServiceImpl")
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
        List<AsuransiModel> listAsuransi = asuransiService.getAsuransiList();
        model.addAttribute("listAsuransi", listAsuransi);
        model.addAttribute("pasien", pasien);
        return "form-add-pasien";
    }

    @RequestMapping(value = "/pasien/tambah/berhasil", method = RequestMethod.POST)
    public String submitPasien(@ModelAttribute PasienModel pasien, Model model){
        pasienService.addPasien(pasien);
        model.addAttribute("pasien", pasien);
        return "add-pasien";
    }

//    //lihat pasien berdasarkan NIK pasien
//    @RequestMapping(path = "/pasien?nikPasien={NIKPasien}")
//    public String viewPasien(@RequestParam(value="NIKPasien") Long NIKPasien, Model model){
//        PasienModel pasien = pasienService.getPasienByNIKPasien(NIKPasien).get();
//        model.addAttribute("pasien", pasien);
//        return "view-pasien";
//    }
}
