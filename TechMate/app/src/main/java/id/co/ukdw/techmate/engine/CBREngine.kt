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
    private val recommendationResult = mutableListOf<Pair<GadgetCase, Double>>()

    init {
        val db = GadgetDatabase.getDatabase(ctx)
        mDao = db.gadgetDao()
    }

    fun insertCases(){
        val data = listOf(
            GadgetCase(0,"apple", 64, 4, 6000000, "touch id, smaller screen", "https://cdn.eraspace.com/media/catalog/product/i/p/iphone_se_3_product_red_1_4.jpg","iPhone SE", "The new iPhone SE delivers best-in-class performance and great photos for an affordable price, if you can live with a small screen",0.0),
            GadgetCase(0,"apple", 128, 6, 13990000, "cinematic mode", "https://cdn.eraspace.com/media/catalog/product/i/p/iphone_14_purple_1_3.jpg","iPhone 14", "The Apple iPhone 14 plays it safe, both in its design and hardware, and that makes the iPhone 14 Pro the more attractive option this time than it may have been in previous years",0.0),
            GadgetCase(0,"apple", 128, 6, 17999000, "cinematic mode, dual camera, better battery life", "https://m.media-amazon.com/images/I/71emcsxsRPL._SL1500_.jpg","iPhone 14 Plus", "The iPhone 14 Plus is the best iPhone for those who want a large display but are not willing to spend more than a grand for a new handset",0.0),
            GadgetCase(0,"apple", 128, 6, 19999000, "cinematic mode, dual camera, better battery life, dynamic island", "https://cworld.id/wp-content/uploads/2022/10/iPhone_14_Pro_Silver_PDP_Image_Position-1A_Silver_Color_SEA-scaled.jpg","iPhone 14 Pro", "Apple has really nailed its smartphone formula. The iPhone 14 Pro features a lot of camera upgrades and the Dynamic Island, which replaces the infamous notch five years after the its debut on the iPhone X.",0.0),
            GadgetCase(0,"apple", 256, 6, 21999000, "cinematic mode, triple camera, dynamic Island, ceramic shield, dynamic island, bigger screen", "https://johnlewis.scene7.com/is/image/JohnLewis/109467278","iPhone 14 Pro Max", "Apple has really nailed its smartphone formula. The iPhone 14 Pro Max features a lot of camera upgrades and the Dynamic Island, which replaces the infamous notch five years after the its debut on the iPhone X.",0.0),
            GadgetCase(0,"samsung", 256, 12, 24999000, "foldable, s pen, super amoled screen", "https://images.samsung.com/id/smartphones/galaxy-z-fold4/images/galaxy-z-fold4_highlights_kv.jpg","Samsung Z Fold 4", "The Z Fold 4 is really the first time the biggest Samsung folding smartphone can be recommended to most people",0.0),
            GadgetCase(0,"samsung", 128, 8, 13999000, "foldable, colorful, good ultrawide camera, compact design", "https://images.samsung.com/id/smartphones/galaxy-z-flip4/images/galaxy-z-flip4_highlights_kv.jpg","Samsung Z Flip 4", "Now the Z Flip is back for 2022, with better cameras, a faster chip, longer battery life and more customisation options than ever",0.0),
            GadgetCase(0,"samsung", 128, 8, 4000000, "4 camera, 120hz refresh rate, good chipset performance", "https://images.samsung.com/is/image/samsung/p6pim/id/sm-a236ezkwxid/gallery/id-galaxy-a23-5g-sm-a236-sm-a236ezkwxid-533613888","Samsung A23 5G", "The Samsung Galaxy A23 comes with 6.6-inch display and Qualcomm Snapdragon 695 5G processor. Specs also include 5000mAh battery with 15W charging speed, Quad camera setup on the back with 50MP main sensor and 8MP front selfie camera",0.0),
            GadgetCase(0,"samsung", 64, 4, 1699000, "depth camera, extended ram, dolby atmos audio", "https://images.samsung.com/is/image/samsung/p6pim/id/sm-a045fzkgxid/gallery/id-galaxy-a04-sm-a045-sm-a045fzkgxid-534064348","Samsung A04", "Samsung Galaxy A04 Android Smartphone was launched in August 2022. The phone has 32GB 4GB RAM and internal storage is expandable using microSDXC (dedicated slot).",0.0),
            GadgetCase(0,"samsung", 64, 4, 1999000, "side fingerprint, triple camera, dolby atmos", "https://images.samsung.com/is/image/samsung/p6pim/au/feature/others/au-feature-galaxy-a04s-a047-535073852?","Samsung A04s", "Expand your view to the 6.5-inch Infinity-V Display on Galaxy A04s and see what you've been missing. Thanks to HD+ technology, your everyday content looks its best—sharp, crisp and clear.",0.0),
            GadgetCase(0,"xiaomi", 256, 8, 13000000, "120hz refresh rate, triple camera, profocus camera, turbo charging 120w, harman kardon sound, dolby atmos", "https://i01.appmifile.com/webfile/globalimg/id/cms/0A22C288-A2FB-2C12-5884-859B97CBC6AD!800x800!85.jpg","Xiaomi 12 Pro", "The Xiaomi 12 Pro not only comes with a new design, but also an entirely revamped camera setup with three highly capable 50-MP sensors",0.0),
            GadgetCase(0,"xiaomi", 256, 8, 5000000, "triple camera, turbo charging 67w, dolby atmos, 5g", "https://i01.appmifile.com/v1/MI_18455B3E4DA706226CF7535A58E875F0267/pms_1658146375.24129894.png","Xiaomi 12 Lite", "Xiaomi 12 Lite comes with featherweight slim design, Studio-level 108MP triple camera, smart 67W turbo charging and 120Hz AMOLED display.",0.0),
            GadgetCase(0,"xiaomi", 64, 4, 1850000, "90hz refresh rate, quad camera, stereo audio", "https://i01.appmifile.com/v1/MI_18455B3E4DA706226CF7535A58E875F0267/pms_1631522439.84756871.png","Xiaomi Redmi 10", "Xiaomi officially launched Redmi 10 as one of the mid-range phones to compete in the Indonesian market.\n" +
                    "\n" +
                    "Compared to its predecessor, Redmi 9, Redmi 10 brings improvements from various aspects. One of them is the camera aspect.",0.0),
            GadgetCase(0,"oppo", 256, 12, 10000000, "120hz refresh rate, triple camera, 4k camera, 80w charging", "https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2022/05/24/4147716260.jpg","Oppo Reno 8 Pro", "The Oppo Reno 8 Pro is a decent phone for the price, with a powerful processor, long-lasting battery, good-looking display and fast charging all contributing to creating a solid all-rounder",0.0),
            GadgetCase(0,"oppo", 128, 8, 3500000, "leather feel, ram 8+8", "https://d2xamzlzrdbdbn.cloudfront.net/products/c3f961bc-18bf-420b-b080-15dd013f40e122120841.jpg","Oppo A77s", "Oppo A77s mobile was launched on 6th October 2022",0.0),
            GadgetCase(0,"oppo", 256, 8, 5000000, "touch id, 30x micro camera" , "https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//86/MTA-55136100/oppo_oppo_reno_8_5g_full01_coogvk1u.jpg","Oppo Reno 8", "Oppo's Reno series of mid-range Android phones might not be flashy like the Find X5 Pro or affordable like an A series device, but the useful specs and competitive pricing definitely hold appeal for many.",0.0),
            GadgetCase(0,"oppo", 64, 4, 2100000, "good camera, elegant design like entry-level gadget, water resistant, ", "https://htsg-store-gl.heytapimg.com/image-file/epb/202211/02/vnfvGUfz11AQK5oi.jpg?x-amz-process=image/resize,m_fill,h_960,w_1080","Oppo A17k", "The Oppo A17k (CPH2471) smartphone released in 2022. It is powered by Mediatek Helio G35 chipset, 3 GB of RAM and 64 GB of internal storage.",0.0),
            GadgetCase(0,"vivo", 256, 8, 4300000, "fast charging 44w, extended ram 8gb, 64mp camera with ois, fingerprint underdisplay", "https://asia-exstatic-vivofs.vivo.com/PSee2l50xoirPK7y/1664507407553/663257af5086fab8e6e4d11e259f52ea.png","Vivo V25e", "vivo launched its newest midrange lineup, the V25 series. 3 devices were added to their ever-expanding roster namely the V25, V25 Pro, and the V25e, the most affordable of its lineup which we have in hand.",0.0),
            GadgetCase(0,"vivo", 128, 8, 9000000, "night vision, fast charging 66w", "https://asia-exstatic-vivofs.vivo.com/PSee2l50xoirPK7y/1662536947069/9ad93f5ca722486eb4d4e114439a1087.png","Vivo V25 Pro", "vivo launched its newest midrange lineup, the V25 series. 3 devices were added to their ever-expanding roster namely the V25, V25 Pro, and the V25e, the most affordable of its lineup which we have in hand.",0.0),
            GadgetCase(0,"vivo", 256, 8, 6000000, "amoled screen, ois camera, water resistant, nfc support, 90hz refresh rate", "https://asia-exstatic-vivofs.vivo.com/PSee2l50xoirPK7y/1661421093552/b543536d032c93db224625029d701465.png","Vivo V25 5G", "vivo launched its newest midrange lineup, the V25 series. 3 devices were added to their ever-expanding roster namely the V25, V25 Pro, and the V25e, the most affordable of its lineup which we have in hand.",0.0),
            GadgetCase(0,"vivo", 64, 4, 1900000, "good selfie camera, water resistant, longer battery life, fast fingerprint response", "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2022/12/26/c688c775-ce86-433d-b58a-703e9b61fe43.jpg","Vivo Y16", "In September 2022, vivo Indonesia quietly presented its latest million Android phone called vivo Y16. Still carrying the same concept as the other Y series, this time vivo presents a cheap cellphone that is comfortable for everyday needs.",0.0),
            GadgetCase(0,"vivo", 64, 4, 2400000, "90hz refresh rate, extended ram 4gb, 50mp camera, fast charging 18w", "https://asia-exstatic-vivofs.vivo.com/PSee2l50xoirPK7y/1661742593678/989c937e99b888767265f51a5d4a4c3b.png","Vivo Y22", "This Android phone is here to fill the entry-level market which is still in demand by many people. Therefore, this time vivo presents more updated specifications to be able to compete.",0.0),
            GadgetCase(0,"realme", 32, 3, 1400000, "big screen, thin bezels, side fingerprint", "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2023/2/5/3085656a-95d5-4362-9b92-00c5d2448257.jpg","Realme C30s", "n mid-February 2023, realme completes the realme C Series again by bringing the realme C30s to the Indonesian market. This time, the tagline is \"Advanced Cellphone for a Millions #PastiStylish\".",0.0),
            GadgetCase(0,"realme", 128, 8, 3200000, "fast charging 33w, 5000 mah battery, triple camera", "https://image01.realme.net/general/20221101/1667271058255.jpg","Realme 10", "Realme 10 which was officially released in Indonesia on November 9, 2022. This cellphone is powered by the trending chipset, MediaTek Helio G99.",0.0),
            GadgetCase(0,"realme", 32, 3, 1600000, "ufs internal memory, side fingerprint, good camera","https://image01.realme.net/general/20230217/1676603104101.png?width=1080&height=1080&size=380521", "Realme C33", "This smartphone carries the slogan \"50MP for a Million Affordable\" and claims to be the most stylish in price and camera capabilities.", 0.0))
        executorService.execute { mDao.insertGadget(data) }
    }

    fun initCases() {
        executorService.execute { cases = mDao.getAllGadget() }
    }

    fun getAllGadget() : List<GadgetCase> {
        return cases
    }

    fun recommendation(userInputs: Map<String, Any>) {
        val threshold = 0.8
        val maxDiff = mapOf("memory" to 256, "ram" to 12, "price" to 24999000)
        val weights = mapOf("brand" to 1.0, "memory" to 1.0, "ram" to 1.0, "price" to 1.0, "features" to 0.5)
        val totalWeight = weights.values.sum()
        val recommendedCase: ArrayList<Pair<GadgetCase, Double>> = ArrayList()
        for (case in cases) {
            case.similarity = 0.0
            var similarity = 0.0
            for ((key, value) in case) {
                val userInput = userInputs[key]
                if (value is String && userInput is String) {
                    val stringSimilarity = weights[key]!! * jaroDistance(value.lowercase(), userInput.lowercase())
                    similarity += stringSimilarity
                } else if (value is Int && userInput is Int) {
                    val diff = maxDiff[key] ?: 1
                    val numberSimilarity = weights[key]!! * (1 - abs(value - userInput).toDouble() / diff)
                    similarity += numberSimilarity
                }
            }
            if (similarity / totalWeight >= threshold) {
                case.similarity = similarity / totalWeight
                recommendedCase.add(Pair(case, similarity))
            }
        }
        recommendationResult.addAll(recommendedCase)
    }

    private fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    private fun min(a: Int, b: Int): Int {
        return if (a < b) a else b
    }

    private fun jaroDistance(s1: String, s2: String): Double {
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

    fun getSortedRecommendationResult(): List<GadgetCase> {
        return recommendationResult.sortedByDescending { it.second }.map { it.first }
    }

    fun clearRecommendationResult() {
        recommendationResult.clear()
    }
}