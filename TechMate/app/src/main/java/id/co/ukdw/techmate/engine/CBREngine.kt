package id.co.ukdw.techmate.engine

import android.content.Context
import id.co.ukdw.techmate.data.database.GadgetCase
import id.co.ukdw.techmate.data.database.GadgetDAO
import id.co.ukdw.techmate.data.database.GadgetDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.math.abs

class CBREngine(ctx : Context) {
    private val mDao : GadgetDAO
    private lateinit var cases: List<GadgetCase>
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = GadgetDatabase.getDatabase(ctx)
        mDao = db.gadgetDao()
        insertCases()
        initCases()
    }

    fun insertCases(){
        val data = listOf(
        GadgetCase(0,"apple", 64, 4, 6000000, "touch id, smaller screen", "https://cdn.eraspace.com/media/catalog/product/i/p/iphone_se_3_product_red_1_4.jpg","iPhone SE"),
        GadgetCase(0,"apple", 128, 6, 13990000, "cinematic mode", "https://cdn.eraspace.com/media/catalog/product/i/p/iphone_14_purple_1_3.jpg","iPhone 14"),
        GadgetCase(0,"apple", 128, 6, 17999000, "cinematic mode, dual camera, better battery life", "https://m.media-amazon.com/images/I/71emcsxsRPL._SL1500_.jpg","iPhone 14 Plus"),
        GadgetCase(0,"apple", 128, 6, 19999000, "cinematic mode, dual camera, better battery life, dynamic island", "https://cworld.id/wp-content/uploads/2022/10/iPhone_14_Pro_Silver_PDP_Image_Position-1A_Silver_Color_SEA-scaled.jpg","iPhone 14 Pro"),
        GadgetCase(0,"apple", 256, 6, 21999000, "cinematic mode, triple camera, dynamic Island, ceramic shield, dynamic island, bigger screen", "https://johnlewis.scene7.com/is/image/JohnLewis/109467278","iPhone 14 Pro Max"),
        GadgetCase(0,"samsung", 256, 12, 24999000, "foldable, s pen, super amoled screen", "https://images.samsung.com/id/smartphones/galaxy-z-fold4/images/galaxy-z-fold4_highlights_kv.jpg","Samsung Z Fold 4"),
        GadgetCase(0,"samsung", 128, 8, 13999000, "foldable, colorful, good ultrawide camera, compact design", "https://images.samsung.com/id/smartphones/galaxy-z-flip4/images/galaxy-z-flip4_highlights_kv.jpg","Samsung Z Flip 4"),
        GadgetCase(0,"samsung", 128, 8, 4000000, "4 camera, 120hz refresh rate, good chipset performance", "https://images.samsung.com/is/image/samsung/p6pim/id/sm-a236ezkwxid/gallery/id-galaxy-a23-5g-sm-a236-sm-a236ezkwxid-533613888","Samsung A23 5G"),
        GadgetCase(0,"samsung", 64, 4, 1699000, "depth camera, extended ram, dolby atmos audio", "https://images.samsung.com/is/image/samsung/p6pim/id/sm-a045fzkgxid/gallery/id-galaxy-a04-sm-a045-sm-a045fzkgxid-534064348","Samsung A04"),
        GadgetCase(0,"samsung", 64, 4, 1999000, "side fingerprint, triple camera, dolby atmos", "https://images.samsung.com/is/image/samsung/p6pim/au/feature/others/au-feature-galaxy-a04s-a047-535073852?","Samsung A04s"),
        GadgetCase(0,"xiaomi", 256, 8, 13000000, "120hz refresh rate, triple camera, profocus camera, turbo charging 120w, harman kardong sound, dolby atmos", "https://i01.appmifile.com/webfile/globalimg/id/cms/0A22C288-A2FB-2C12-5884-859B97CBC6AD!800x800!85.jpg","Xiaomi 12 Pro"),
        GadgetCase(0,"xiaomi", 256, 8, 5000000, "triple camera, turbo charging 67w, dolby atmos, 5g", "https://i01.appmifile.com/v1/MI_18455B3E4DA706226CF7535A58E875F0267/pms_1658146375.24129894.png","Xiaomi 12 Lite"),
        GadgetCase(0,"xiaomi", 64, 4, 1850000, "90hz refresh rate, quad camera, stereo audio", "https://i01.appmifile.com/v1/MI_18455B3E4DA706226CF7535A58E875F0267/pms_1631522439.84756871.png","Xiaomi Redmi 10"),
        GadgetCase(0,"oppo", 256, 12, 10000000, "120hz refresh rate, triple camera, 4k camera, 80w charging", "https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2022/05/24/4147716260.jpg","Oppo Reno 8 Pro"),
        GadgetCase(0,"oppo", 128, 8, 3500000, "leather feel, ram 8+8", "https://d2xamzlzrdbdbn.cloudfront.net/products/c3f961bc-18bf-420b-b080-15dd013f40e122120841.jpg","Oppo A77s"),
        GadgetCase(0,"oppo", 256, 8, 5000000, "touch id, 30x micro camera" , "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//86/MTA-55136100/oppo_oppo_reno_8_5g_full01_coogvk1u.jpg","Oppo Reno 8"),
        GadgetCase(0,"oppo", 64, 4, 2100000, "good camera, elegant design like entry-level gadget, water resistant, ", "https://htsg-store-gl.heytapimg.com/image-file/epb/202211/02/vnfvGUfz11AQK5oi.jpg?x-amz-process=image/resize,m_fill,h_960,w_1080","Oppo A17k"),
        GadgetCase(0,"vivo", 256, 8, 4300000, "fast charging 44w, extended ram 8gb, 64mp camera with ois, fingerprint underdisplay", "https://asia-exstatic-vivofs.vivo.com/PSee2l50xoirPK7y/1664507407553/663257af5086fab8e6e4d11e259f52ea.png","Vivo V25e"),
        GadgetCase(0,"vivo", 128, 8, 9000000, "night vision, fast charging 66w", "https://asia-exstatic-vivofs.vivo.com/PSee2l50xoirPK7y/1662536947069/9ad93f5ca722486eb4d4e114439a1087.png","Vivo V25 Pro"),
        GadgetCase(0,"vivo", 256, 8, 6000000, "amoled screen, ois camera, water resistant, nfc support, 90hz refresh rate", "https://asia-exstatic-vivofs.vivo.com/PSee2l50xoirPK7y/1661421093552/b543536d032c93db224625029d701465.png","Vivo V25 5G"),
        GadgetCase(0,"vivo", 64, 4, 1900000, "good selfie camera, water resistant, longer battery life, fast fingerprint response", "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2022/12/26/c688c775-ce86-433d-b58a-703e9b61fe43.jpg","Vivo Y16"),
        GadgetCase(0,"vivo", 64, 4, 2400000, "90hz refresh rate, extended ram 4gb, 50mp camera, fast charging 18w", "https://asia-exstatic-vivofs.vivo.com/PSee2l50xoirPK7y/1661742593678/989c937e99b888767265f51a5d4a4c3b.png","Vivo Y22"),
        GadgetCase(0,"realme", 32, 3, 1400000, "big screen, thin bezels, side fingerprint", "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2023/2/5/3085656a-95d5-4362-9b92-00c5d2448257.jpg","Realme C30s"),
        GadgetCase(0,"realme", 128, 8, 3200000, "fast charging 33w, 5000mah battery, triple camera", "https://image01.realme.net/general/20221101/1667271058255.jpg","Realme 10"),
        GadgetCase(0,"realme", 32, 3, 1600000, "ufs internal memory, side fingerprint, good camera","https://image01.realme.net/general/20230217/1676603104101.png?width=1080&height=1080&size=380521", "Realme C33") )

        executorService.execute { mDao.insertGadget(data) }
    }

    fun initCases() {
        cases = mDao.getAllGadget()
    }

    fun getAllGadget() : List<GadgetCase> {
        return cases
    }

    fun getDetailGadget(id : Int) : GadgetCase {
        return mDao.getGadgetDetail(id)
    }

    fun recommendation(userInputs: Map<String, Any>) : List<GadgetCase> {
        val threshold = 0.8
        val maxDiff = mapOf("memory" to 256, "ram" to 12, "harga" to 24999000)
        val weights = mapOf("brand" to 1.0, "memory" to 1.0, "ram" to 1.0, "harga" to 1.0, "fitur" to 0.5)
        val totalWeight = weights.values.sum()
        var bestSimilarity = 0.0
        var recommendedCase: ArrayList<GadgetCase> = ArrayList()
        for (case in cases) {
            var similarity = 0.0
            for ((key, value) in case) {
                val userInput = userInputs[key]
                if (value is String && userInput is String) {
                    similarity += weights[key]!! * jaroDistance(value.lowercase(), userInput.lowercase())
                } else if (value is Int && userInput is Int) {
                    val diff = maxDiff[key] ?: 1
                    similarity += weights[key]!! * (1 - abs(value - userInput).toDouble() / diff)
                }
            }
            if (similarity / totalWeight >= threshold) {
                recommendedCase.add(case)
            }
        }
        return recommendedCase
    }

    fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    fun min(a: Int, b: Int): Int {
        return if (a < b) a else b
    }

    fun jaroDistance(s1: String, s2: String): Double {
        val s1Length = s1.length
        val s2Length = s2.length

        if (s1Length == 0 && s2Length == 0) {
            return 1.0
        }

        val matchDistance = (max(s1Length, s2Length) / 2) - 1

        val s1Matches = BooleanArray(s1Length)
        val s2Matches = BooleanArray(s2Length)

        var matches = 0
        var transpositions = 0

        for (i in 0 until s1Length) {
            val end = min(i + matchDistance + 1, s2Length)
            for (j in max(0, i - matchDistance) until end) {
                if (!s2Matches[j] && s1[i] == s2[j]) {
                    s1Matches[i] = true
                    s2Matches[j] = true
                    matches++
                    break
                }
            }
        }

        if (matches == 0) {
            return 0.0
        }

        var k = 0

        for (i in 0 until s1Length) {
            if (s1Matches[i]) {
                while (!s2Matches[k]) {
                    k++
                }
                if (s1[i] != s2[k]) {
                    transpositions++
                }
                k++
            }
        }

        return (matches.toDouble() / s1Length + matches.toDouble() / s2Length + (matches - transpositions / 2.0) / matches) / 3.0
    }
}