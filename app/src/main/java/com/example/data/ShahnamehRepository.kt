package com.example.data

import com.example.model.BattleSkill
import com.example.model.CharacterNode
import com.example.model.LaborItem
import com.example.model.MythicWaypoint
import com.example.model.Story
import com.example.model.ThematicCollection

object ShahnamehRepository {

    const val LOGO_URL =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuCsW_ExEFL9eZOMcru9xDLHpPlZr8tM9L57Sa9Dr5rRdtZ7hpwrsLf0KcwDbcnVCdmRX3qe4qyylwGMsg5x1MNmvJ4-w3FtHauPgWCDNVqz98niA60FDt3n_bWUMAFWggcajDGUql3-GgygLUIOvk28gHfR_ToMEMZK5TIhG1ceiDdW1XO_pZea_qmv9wpEWMLCvKttltbBUvroVA-sr5dCrT2Pw3xsIIyIl_MKE-aZE8dgst4qSo92"

    const val BATTLE_SCENE_URL =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuBvaAy5SMW0AbG5I3xo0mXc5Gmnr3rH_SiqmoUqsvkWemWIt4KJpPQ9EkEo3AyeoXAQ3e140PqfykQ5wTq0VuqT2OQk9y88PmScvHTFsuLVuqhSwZ3ir-x82a3NQkXh7q15SuBaYdchty7B9niTM2J5894z6yvy3wB-8Oe_fYTuf1z42YAmeP1TGvRiJtTQX6xNXZ6WAffB6qMv-DBs8-fTQ8JyevjWZVmNgd7xq26KEo0DIK4GllFR"

    const val ROSTAM_URL =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuD39VtCk5G5YkXOkyMzGrTWl9iGGN6kbdP5T2zhhNb8V1pP8oK79fD4dpAyiY0r1IpSLG9-DDgiNN4m-YuQhmuO1U1__X4e7FWNVcQPesax0eB0-Monflr3VaC3zt-qgllY5GieawGRIuA10xwHXUW5uWM4-3b_XmTXfh_22Fv_W7e6Mn59V3mauMOCxuFqGmKA1DLoRmwQkrJ0lgKDgBWnJb_mTeATsnFtdZp80HdWK2Aafu9UooOz"

    const val ROSTAM_PALACE_URL =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuA5HsKRSU30nnh46m6BJW1n86OelwiBEF5NskqAGTCm-layLAMXPpGs4f-FitTzm93fzkIJK4oM_p9XnRQ96gIU4BWOB6O8HYqQcCSN0giyaXcKlTwWM5I7Q0XzPZ6tmU2BbAlaUHuscdNc3YxMGWvxSt3pq52cvYcz7HMY6HtQg3AuY4LNCBHTRohtWSV3b3euy1Qg4Ya5Iw2LGE1lHhc6f-P8J14OAOQA-3xvAtngHF4ItyOG4Wq"

    const val HEALING_KAVUS_URL =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuDhf-oieQ951OD66wHjbIrZikSSPZPcwlFG_cU2cxfF5wklv0mLlLnpfr7zxJjiYVZ31w0KH-j-kL7RWVVvXxemT-6kI7DRdHfJdg6dxN67WaZrldFaV0L48gSj4XYle3W2-QGlEGGpATbASb-pUaEHfp0Z00c9N8nUu6qxtra1mZzDATEUE8yWzyBCwxDZrrH6If7a3I9q1xiJgcF2ctJXwLIST4OFL1PQjSOaBBW0GFf_kxczbmYB"

    const val DRAGON_BATTLE_URL =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuAtLpXCuaS4w5J_hGPgvFrVKfop2-PGXhtlUePlfA6o8sfXtzsa0xckDE2ez583C9QPmlmGPlRfyco7NoC1kZkLbJuijxMWuKZrWWRh-hBK8hs2uJKPmUc1rbtprJ_-zXH4P7uLo0lfV6luDrmsnjfVHi7f-O-izp_s7oqXXlE6y488LB9I8A5iMoOzuOEMdutlt1Fa6aiPoqkiTuqv6flSV1eKKCPSl03osgQA66oHiHaiik8ouy3V"

    const val MYTHIC_MAP_URL =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuDZIvk7NFkSu3WFs_2lAwz28_y2T3WrUWOcqbmW3ZwNlgz7ZzeY1kLKCqZf8zgyxeuee9YY33bwo_a8OiB0JIlPl--y1FJ4U3tB1Oy2cQmFpDmqRqBUgWx8q1IccldsEJU_GSMF69sRxkuMcglKdB86sLoPpdv3B4Z0UMdKmpML_SMLOOCfBuAqoI_VXzwwCCB0akltDixyGQRftXknASdKuF0PNRwzsPfxbm5ucJ97x9GXAZZi6N1z"

    const val ROSTAM_SOHRAB_URL =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuC9OG2BYHI67TlNzVoXatNLwqAh4DfRHj-scVD6tDp0bqMZpq2omfO8TnzzXkMdu9WJtC18bTq1rDkTZyf32F1nSPWG0FRYrGNfeKkcTLC_6Q-T7LBM9DfQc_4pJsUPuaRHW8HkrcSq18DKy0TCfucEpUYB4aD8Yod-HjlEmZh1ZqGvF-c5mkQPR9Sct2S7I_TmGnARCFhdLrCqee337_CQgvYN43wVFzSIdN1N4cKWpQEUGA3bT8k_"

    const val RAKHSH_URL =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuBUBtDo1MSy1tOproQ9EMU7I-WOK9VuAhBrIUDBJzZUwTwN-PiuLa8xNsguY73X4yFHlEk-OjOqpXq1tgFtXSyTPt3P_wBEapWKgAmI5a5xDCQoZDmGgrOXVJN3Oy-mML9S106UmXttNB-HhE7t8og0Cx8h9dYWSno67LUVIsXzI_gbRMa8XYJsChJPoDuTrESyoSEwy7JXFpFa4297H-xatB_PLNFSf3qxuewUrL5horUYGJ0P9mln"

    const val BABR_BAYAN_URL =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuDDNY0Hss9CNjGlLfRdKaOFJhUaNSPUwstQuappQfCHoXU3BtppeCW4VP5_LdQ8v0wxzvhwvWOQrJVMwkl6NBWEh4psl63BV0n1_0F-rICOqXox8d_B7wMTqE6JzZE2dhpXYiEJ7LuwkLsVP65ALiWnFEDc2dF3xPepT1Yx9FZIVUoh8_BVMMNMTarLkY8OMZJ9R6aahE4JaKm2NzWztN4ufOkry0QmKjV3YIZkzTuHJ-yo_F8or8wM"

    const val GORZ_GAVSAR_URL =
        "https://lh3.googleusercontent.com/aida-public/AB6AXuA75fckp6FTjAqSViIL3i9rMnls7J_y6fZ-TEXFCXklEXQthezZsyi_miLZaz1SyWjxVU8T1eTj8YNyGku1oJhn7FiS69nEQnU8F0jQLlglTZKo8Pvo0KVQD6kbui1K0XD4nIuT8dJn1HO5G6euvTmp2Ic7uCRNCsMpAB-JD7sybeJnQStNFeaD0ObV9MIqFPAjEgHYfXFwDdp7mbJf5Ml5YzLHTZAH6a2g3ieiSPQU-9fa7nSfj51V"

    // Characters in the Genealogy Tree & Pantheon
    val characters: List<CharacterNode> = listOf(
        CharacterNode(
            id = "rostam",
            name = "رستم دستان (تَهمتَن)",
            englishName = "Rostam Dastan",
            title = "جهان‌پهلوان ایران‌زمین • Guardian of Iran",
            dynasty = "سلسله: پهلوانان سیستان و مدافع شاهان کیانی",
            imageUrl = ROSTAM_URL,
            badge = "شخصیت برگزیده",
            badgeIcon = "🛡️",
            quote = "«به بازوی رستم میان را ببند / که گیتی ندیده‌ست چون او نژند»",
            father = "زال زر (پهلوان سپیدموی)",
            mother = "رودابه (شاهدخت کابل)",
            spouse = "تهمینه (بانوی سمنگان)",
            children = "سهراب، فرامرز، بانوگشسپ",
            powerPercent = 100,
            farrPercent = 96,
            lifespan = "۶۰۰ سال (کی‌قباد تا بهمن)",
            steed = "رخش گلگون (همزاد و همراه)",
            realm = "زابلستان و نیمروز اساطیری",
            biography = "بزرگ‌ترین و نام‌آورترین پهلوان شاهنامه، فرزند زال و رودابه و پدر سهراب. او در طول دوران پادشاهی چند نسل از شاهان کیانی، مدافع مرزهای ایران، گذرنده از هفت‌خوان و دارنده رخش تیزتک بود.",
            timelineHighlights = listOf("هفت‌خوان رستم", "نبرد با دیو سپید", "تراژدی سهراب", "رزم اسفندیار")
        ),
        CharacterNode(
            id = "kayumars",
            name = "کیومرث",
            englishName = "Kayumars",
            title = "نخستین شهریار جهان • پیشدادیان",
            dynasty = "پیشدادیان",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBcWiBTJToSn1DpGqJ3SCjb3hxcI1SfFZxtBaoyBCB7zLQxB0Sl_BK1ERZ0RPkGrfV5JoDWauWz9mZraYlOqF81kIXWbCsUdNLu2cNpWqMPOBalf1xcyxQDS3n5V8xNdNkPbhzy81upefxGZOSGm4ZyCXJuf9e1gWgGQJLSNkM1oz0RFNcjea6A3UTiPB0CW-5KuQSZxdkEGGKWeE1eBkxggTEysQqwoWzcsIe2dw7GTu2wu51l9Koj",
            badge = "تاج نخستین",
            badgeIcon = "👑",
            quote = "«چو آمد به برج بره آفتاب / تابان شد اندر جهان آب و تاب»",
            powerPercent = 85,
            farrPercent = 98,
            biography = "نخستین پادشاه اساطیری ایران و بنیان‌گذار سلسله پیشدادیان که مردم را آیین زندگی و پوشش آموخت."
        ),
        CharacterNode(
            id = "jamshid",
            name = "جمشید شاه",
            englishName = "Jamshid",
            title = "شهریار زرین‌عصر • صاحب جام جم",
            dynasty = "پیشدادیان",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBJl4UAauFvqWqkk9w8fPkJAEIWd_zmCsmb9uLI8B_K2aH2wElWcPudvl_9s_W3ay-G1iRPmbDa5UT9X0eR6J_dr8Io23SmoGpdXgs__bCeDbmVE2yd3Yxst4GJ-fEVyVpTKwfiiKXT9e9OTDG83J15TX9xHiQ0PldQn-g-RI_jNdViO_Ba01Cw_PsD6HtRXtlCaaLhgxlHyzB0aO0pRDVlRWFqtCUTzYtt0CIHu3Pq7SNHoU_Ge9L1",
            badge = "خورشید فرّه",
            badgeIcon = "☀️",
            quote = "«منم گفت با فرّه ایزدی / همم شهریاری و هم بخردی»",
            powerPercent = 92,
            farrPercent = 99,
            biography = "پادشاه پرشکوه پیشدادی که جشن نوروز را بنیان نهاد، طبقات اجتماعی را سامان داد و هفتصد سال دوران طلایی را رقم زد."
        ),
        CharacterNode(
            id = "fereydun",
            name = "فریدون فرخ",
            englishName = "Fereydun the Just",
            title = "شاه دادگر و نابودگر ضحاک",
            dynasty = "پیشدادیان",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuChRJ9_GxYrl9nL4Bbb_C45D8kultiLCl4OdzKQdJq36dbP1FoY1D2XXEjFmqyXvAXoE9mJ-EBZtCgSkkqp2yatdxby-lmvYqAb-n-Tl7llUOkrWZMcMPgRHbkuJ0rtCsTWQK1o09Jk9cwsBOCaT_gYE-Ai5UJT7mVF-VdLaWng5AZ3HWbjd-FfJXSxOL7CFPsUQ4ZHSLiEa3ptrAwRGQKIiOQxcxh-wrP8ClLEQANYCq6FzkzsrUG8",
            badge = "فرّ ایزدی",
            badgeIcon = "✨",
            quote = "«فریدون فرخ فرشته نبود / ز مشک و ز عنبر سرشته نبود»",
            powerPercent = 94,
            farrPercent = 100,
            biography = "شهریار فرهمندی که با گرز گاوسار و همراهی کاوه آهنگر بر ضحاک ستمگر پیروز شد و دادگری را در جهان گسترد."
        ),
        CharacterNode(
            id = "sam",
            name = "سام نریمان",
            englishName = "Sam of Sistan",
            title = "سپهسالار سیستان و نیای رستم",
            dynasty = "پهلوانان سیستان",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAOQcyQz8dsEQ3yWXmlIwOslX7tPrxsneRLemfIg6ox66vYwno2YNzzaPk8swjiRt_N_A_gWwSrK6HS-kRYhAr5AGdblPz4htslLoBxUkSZ2RGWKujzE9pD17-1pOIFCqmzQz5Wjg-7JnsczDU6MsR1w5eG5G9c7s8YILvqiuAatSI720Iz2-E4IsPzAFnB7Jppn-1xSXpzyxp_CEuyKQ73c7zUdhZRtc_KMCrP1-A2XerEpaLOdWQW",
            badge = "گرز گاوسار",
            badgeIcon = "⚔️",
            quote = "«چو من تیغ هندی برآرم ز جای / ز ناله بلرزد همی سنگ و خای»",
            powerPercent = 95,
            farrPercent = 91,
            biography = "پهلوان اژدهاکش سیستان، پدر زال و نیای رستم دستان که پناهگاه مرزهای شرقی ایران در برابر توران بود."
        ),
        CharacterNode(
            id = "zal",
            name = "زال زر (دستان)",
            englishName = "Zal & Rudabeh",
            title = "پهلوان سپیدموی • پرورده سیمرغ",
            dynasty = "پهلوانان سیستان",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDwR9rt4pIxrwpu80DeqsAm7LMvWACtfDF9MiWSoaEUpX2sXucDfa9lUwL0h_hO8k8ENDa1HMWf0SoMeLeQiVB9Sq1fp92JsRPBLhpu8z34_h1WGUB49lXmCLzdIpJz3-JrrD_Ah2QbtvBq3kZJEHtAubCo2xvHdBARfIOt9dkkLOyJCMy4pLRb7qMHTNYH4bCNOTAaw6em-0z7cIVfpbLDyks1w1Zfvf3L_luSvwqU06ZSkDAbbpir",
            badge = "سیمرغ‌پرور",
            badgeIcon = "🪶",
            quote = "«یکی پر از این پرّ من برگیر / بمان تا بداری مرا یادگیر»",
            father = "سام نریمان",
            mother = "نیک‌اختر",
            spouse = "رودابه (شاهدخت کابل)",
            children = "رستم، زواره",
            powerPercent = 93,
            farrPercent = 97,
            biography = "پهلوان نامدار سپیدموی که سیمرغ در ستیغ البرز پرورش داد و مشاور دانا و گره‌گشای شاهان و پهلوانان بود."
        ),
        CharacterNode(
            id = "siyavash",
            name = "سیاوش پاک‌زاد",
            englishName = "Prince Siyavash",
            title = "شاهزاده پرهیزگار و نماد راستی",
            dynasty = "کیانیان",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBTFJ7gFuNVZut0wRn0SDIQWe5ko2JwRLf23SnE-t-BNVSG-iuMSwa06pJCabXW92-Onps43Hbyk6cm7FLk_1xL2x8F6hBGJAII0ucC-XMGdFV3_4ueNSxUm_AA_9UJea7ezUFIyKGARDfJHdGc0A1jncBk0TR5d9SA7Yd1aPyihZrvMMo_ApnZdNEshqw0OzUcQuuTupidaU1aTOBW13fm6Cao9yVGCFIy9DItqWOO_itYKFJN7kH1",
            badge = "گذر از آتش",
            badgeIcon = "🔥",
            quote = "«چو بخشایش پاک یزدان بود / دم آتش و آب یکسان بود»",
            father = "کی‌کاووس شاه",
            mother = "شاهدخت تورانی",
            spouse = "فرنگیس (دختر افراسیاب)",
            children = "کی‌خسرو شاه",
            powerPercent = 88,
            farrPercent = 99,
            biography = "فرزند پاکدامن کی‌کاووس و پرورده دست رستم در زابل؛ از کوه آتش برای اثبات بی‌گناهی به سلامت گذشت."
        ),
        CharacterNode(
            id = "sohrab",
            name = "سهراب جوان",
            englishName = "Sohrab",
            title = "دلاور دژ سپید و پور تهمتن",
            dynasty = "پهلوانان سیستان / سمنگان",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDanyBE41dmftvy0eHqm1NlMsNOBWt3hr6YtebYouD7tR7lgkZcLi2EAE1l8e8UqB11cWZfDcgcJ1mE0pcJRjP85DP0vt7t9l9k1ZYGHccHptlXUpL0TqhpuEYN6-1KtxG8SEU9lIqJPinJyfovuALtRAAD-gVMLnKXjONVJ-QXstTS4UfvM86CPkq1EX-WUBUMRQnijvex8mT_Yl2q_pAmZyEBCbG3srwsKFvVo5Rm1tLoGjeDsezG",
            badge = "مهره بازو",
            badgeIcon = "💔",
            quote = "«کنون گر تو در آب ماهی شوی / و یا چون شب اندر سیاهی شوی... پدر چون بداند بدوزد کلاه»",
            father = "رستم دستان",
            mother = "تهمینه (بانوی سمنگان)",
            powerPercent = 98,
            farrPercent = 89,
            biography = "پهلوان نوجوان و شجاعی که به سودای یافتن پدر و نشاندن او بر تخت شاهی تاخت، اما فرجامش تراژدی بزرگ تاریخ شد."
        ),
        CharacterNode(
            id = "faramarz",
            name = "فرامرز دلاور",
            englishName = "Faramarz",
            title = "سپهدار و فرزند رستم",
            dynasty = "پهلوانان سیستان",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDt7e_FrnGfNPAfQ74yN4c1RcFx1uLc4eg0-bL4DEtYCZGAITZULDOe53-HvjwAq9wCfeGnLO1ub_gQ8kMVQx1UcDEUzdvusPIB934EzeEA4InSUwofS6S5PeUi57zQLsojS6Dh_O0PGglw-oqOFthzsdufSP74XsL_8xh7Gke8fhYWih672XxP142sf05Kb1KUXGRT-dpZ21r-w5RIpIDSasjr4XUpenXCe1Qk9U-P8M3dnAzPn5EL",
            badge = "تیرانداز زابل",
            badgeIcon = "🏹",
            quote = "«فرامرز با گرز و با تیغ تیز / برانگیخت بر گرد شبدیز خیز»",
            father = "رستم دستان",
            powerPercent = 91,
            farrPercent = 90,
            biography = "فرزند دلیر رستم و فرمانده پیشقراول سپاه ایران در نبردهای کین‌خواهی سیاوش."
        ),
        CharacterNode(
            id = "kaykhosrow",
            name = "کی‌خسرو شاه",
            englishName = "Kay Khosrow",
            title = "شهریار دادگر و معنوی کیانی",
            dynasty = "کیانیان",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDtJblbTN1nExWS53WpD1w2QHG9KEd8DfdkkmoHBIh_VyhiiF1oXTxWS6v8dlbOuhuqLyecTYP6wmlSorVTPbRCQrSGxoLGbo1sH636jm7p-bpBXYq9wEpho3DUvEpWNgoe_eRno41bkpSUjl_Cowg9gTVZUb5RyBp76RYu3RxeMTH1rb5j5mDqmg2ttjUoyry0e_NRNbUOQdc7FLaMLdIFzKz9Bij7ydIaIAj57eyYuAaFWxb5R_Du",
            badge = "فرّ اعظم",
            badgeIcon = "✨",
            quote = "«جهاندار پیروز و بیدار باد / همیشه جهان زو پدیدار باد»",
            father = "سیاوش",
            mother = "فرنگیس",
            powerPercent = 94,
            farrPercent = 100,
            biography = "پادشاه آرمانی شاهنامه که کین سیاوش بگرفت، افراسیاب را مغلوب ساخت و سرانجام به درگاه ایزدی عروج کرد."
        ),
        CharacterNode(
            id = "kaykavus",
            name = "کی‌کاووس شاه",
            englishName = "King Kay Kavus",
            title = "شهریار ماجراجوی کیانی",
            dynasty = "کیانیان",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCAV5lyY6aYHgv-Oq_J0y1SPC3gHp1kkQgy0nJZVynFRM6escAYsgeg3f5HLnDUh9bBdvZlQQMPB3lyCpnZ2NmoLqGCV0Xr1mWJkso2JtX7HyqMHesePHmiTqry_UapNUyyhn6xgfl7-_JnuBhYmEgaepXmpeWssnV_2ZKLGiJAADY1ZeCgm7b4ZxBqRJEuVF3C60TviM1NGOY5MgYwheq8VxrxLhnAOmaQpmjtWek1m5FDWLc45KF8",
            badge = "تخت پرنده عاج",
            badgeIcon = "👑",
            quote = "«چو اندر کشیدند در چشم شاه / بشد تیرگی باز و روشن نگاه»",
            powerPercent = 86,
            farrPercent = 88,
            biography = "شهریار بلندپرواز کیانی؛ فاتح مازندران و هاماوران که با بلندپروازی‌های بی‌حد، آزمون‌های بزرگ برای رستم آفرید."
        ),
        CharacterNode(
            id = "roudabeh",
            name = "رودابه مهراب‌دخت",
            englishName = "Princess Roudabeh",
            title = "شاهدخت کابلستان • مادر رستم",
            dynasty = "کابلستان / سیستان",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuC0evjhmG5vJR6-OvfxC2OjOEIB2ti8p5kJLMyzdjGTvRmx0FDqrNIw3BtePHy5y-ZY0IsVuDx8jSHjEhwt-Bw02-d0PK-jK2GAiip1tfKYE5cOqtSMDd_9Q_a6Jngzo4VA5VEbgrHVsd0wLzY9sz46Q1fLtF9DwHEX2-feyztPE-auAcRMY2srcuUCh7VsLQ3KaAYKy26T3QZhYtzeIDDJ_e36SiIJpyUdHPPbXoxKyeg-pGjJn-49",
            badge = "گیسوی کمند",
            badgeIcon = "🌸",
            quote = "«کمندی گشاد از سر ماهروی / که مشکین و پیچان بدی تا به پای»",
            spouse = "زال زر",
            children = "رستم دستان",
            powerPercent = 80,
            farrPercent = 95,
            biography = "نماد دلدادگی شجاعانه و پیوند تبارها؛ گیسوفروهشته به کمند عشق زال و نخستین مادری که با عمل جراحی (رستم‌زایی) زایمان کرد."
        ),
        CharacterNode(
            id = "div_sepid",
            name = "دیو سپید مازندران",
            englishName = "Div-e Sepid",
            title = "سالار جادوگران و دیوان • خوان هفتم",
            dynasty = "اهریمن‌تباران مازندران",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBP4aK7Nc9A6xCMurW9g16-7O_za-Q3HRokzEz9PkgRSc7_G2J7gp1GvyDvR6cXNVHmEeCdkrzs0LMFRv7t1xB6XC_IJGh_ixuFXTQNQ7b_Y5psepzmw4gtni4oi6PqXsF1YnGxGGIcpKRC8D4mgRjBmhKf60u1asloAKTGScY6OXFm-0-5CQHhveYlgAuNqa1gbqFYkHQXo46nWFSfr-AXvCNakUFCkK3HTcvCm3NEWg4R952DE19m",
            badge = "جادوی سیاه",
            badgeIcon = "💀",
            quote = "«فروهشت شمشیر و ببرید پای / فغان و خروش آمد از خوابگاه»",
            powerPercent = 97,
            farrPercent = 20,
            biography = "فرمانروای تیره سرزمین مازندران؛ ساحری که سپاه کی‌کاووس را نابینا کرد و جگرگاه او دوای بینایی چشمان سپاه ایران شد."
        )
    )

    // The 7 Labors of Rostam (هفت‌خوان رستم)
    val labors: List<LaborItem> = listOf(
        LaborItem(
            number = 1,
            title = "خوان نخست: نبرد رخش با شیر بیشه",
            subtitle = "دلیری رخش در پاسداری از رستم خفته",
            tag = "پیروز • همرزم: رخش",
            location = "سرزمین نیشابور تا کلات",
            verse = "«چو بیدار شد رستم تیزچنگ / بدید آن بر و بافهٔ شیر و رنگ... بگفت ای رخش هوشیار من / که بستود بادا سپهدار من»",
            description = "هنگامی که رستم به خواب عمیق فرو رفته بود، شیری ژیان به قصد او تاخت. رخش با هوشمندی بی‌نظیر با دو دست بر سر شیر کوفت و او را در هم شکست تا خواب تهمتن آشفته نشود.",
            keyToVictory = "وفاداری و پایمردی رخش تیزتک",
            ally = "رخش"
        ),
        LaborItem(
            number = 2,
            title = "خوان دوم: گذر از بیابان سوزان و عطش",
            subtitle = "امداد اهورایی و نمایان شدن میش کوهی",
            tag = "پیروز • آب حیات",
            location = "کویر ریگ و بی‌آب مازندران",
            verse = "«یکی میش دید اندر آن خارستان / خرامان همی رفت بر بوستان... ز آبش بشستند و تیمار کرد / تن خسته از تابش گرم گرد»",
            description = "تشنگی جانکاه تن رستم و رخش را ناتوان کرد. رستم دست نیایش به درگاه دادار بی‌همتا برد؛ ناگاه میشی کوهی پدیدار شد و تهمتن با پیروی از ردپای او به چشمه‌ای گوارا دست یافت.",
            keyToVictory = "نیایش پاکیزه و فرّ کیانی",
            ally = "میش هدایت‌گر"
        ),
        LaborItem(
            number = 3,
            title = "خوان سوم: پیکار با اژدهای هولناک",
            subtitle = "همراهی رخش در دریدن تن مار دژم",
            tag = "پیروز • هلاکت اژدها",
            location = "بیشه تاریک و مه‌آلود کوهسار",
            verse = "«چو بیدار شد رستم و رخش را / برآشفت و بشکست خفتان و خود / بینداخت خنجر چو شیر ژیان / سر اژدها کرد ز تن جدا»",
            description = "اژدهایی که حتی عقاب بر فراز قلمروش جرات پرواز نداشت، رستم خفته را غافلگیر ساخت. رخش فداکارانه جنگید و تهمتن با تیغ تیز سر از تن هیولا جدا کرد.",
            keyToVictory = "تیغ سام نریمان و هوشیاری رخش",
            ally = "رخش گلگون"
        ),
        LaborItem(
            number = 4,
            title = "خوان چهارم: رویارویی با زن جادوگر",
            subtitle = "خواندن نام ایزد پاک و باطل شدن طلسم فریب",
            tag = "پیروز • کشف مکر",
            location = "چشمه‌سار نغمه‌خوانان جادو",
            verse = "«چو آواز یزدان نیوشید زن / دگرگونه گشت اندر آن انجمن... کمند افکند پهلوان سرش / به دونیمه کرد آن تن بدکنش»",
            description = "زنی جادوپیشه با چهره‌ای آراسته و تنبور نوازنده در کنار بزمگاهی سرسبز به رستم نزدیک شد. رستم جامی می برگرفت و نام دادار پاک برد؛ در دم چهره زن دگرگون و سیاه گشت و کمند رستم او را به بند کشید.",
            keyToVictory = "بر زبان آوردن نام یزدان پاک",
            ally = "فرّ ایزدی"
        ),
        LaborItem(
            number = 5,
            title = "خوان پنجم: بند کشیدن اولاد مرزبان",
            subtitle = "تسخیر مرزبانی مازندران و به‌خدمت‌گیری راهنما",
            tag = "پیروز • اسارت دشمن",
            location = "کشتزارها و بیشه اولاد",
            verse = "«بینداخت رستم کیانی کمند / سر پهلوان شد به دام گزند... بیامد پیاده بدان بارگاه / که بنمایدش بند دیوان و راه»",
            description = "پس از درگیری دشتبان با رستم، اولاد مرزبان با سپاهی انبوه تاخت. رستم سپاه او را پراکند و اولاد را با کمند اسیر کرد و از او پیمان گرفت تا مسیر مخفی غار دیو سپید را فاش سازد.",
            keyToVictory = "تدبیر، کمند شصت‌خم و پیمان‌داری",
            ally = "کمند کیانی"
        ),
        LaborItem(
            number = 6,
            title = "خوان ششم: جنگ با ارژنگ دیو و گشودن دژ",
            subtitle = "کندن سر سالار دیوان و پراکندن لشکریان تاریکی",
            tag = "پیروز • شکست ارژنگ",
            location = "کوه هفت‌کوه مازندران",
            verse = "«چو رستم بر ارژنگ بگشاد بر / سر و گوش بگرفت و برکند سر... دلیران دیوان ز بیم گزند / گریزان شدند از کوه و بند»",
            description = "رستم به اردوگاه ارژنگ‌دیو شبیخون زد، چون صاعقه نعره کشید و با دستان پولادین سر سردار دیوان را از تن برکند و به میان لشکریان دیو افکند تا همگی بگریزند.",
            keyToVictory = "شجاعت بی‌باکانه و هیبت تهمتنی",
            ally = "گرز سام"
        ),
        LaborItem(
            number = 7,
            title = "خوان هفتم: کشته شدن دیو سپید",
            subtitle = "شکافتن سینه سالار دیوان و احیای بینایی ایرانیان",
            tag = "شاهکار نهایی",
            location = "غار تاریک اسپورز در مازندران",
            verse = "«فرو برد خنجر به تاریک غار / برون تاخت آن اژدهای دژار... دل دیو بفکند و برکند هوش / کی کاووس بینا شد از فر و توش»",
            description = "نبرد مرگبار در قعر تاریک غار اسپورز؛ رستم پای دیو را قلم کرد، سینه او را بشکافت و سه قطره از خون جگر دیو را در چشمان نابینای کی‌کاووس شاه و سپاهیان ریخت تا بینایی بازگردد.",
            keyToVictory = "تیغ گیتی‌فروز، ببر بیان و عنایت ایزد",
            ally = "اولاد و رخش"
        )
    )

    // Stories for the Epic Tales tab
    val stories: List<Story> = listOf(
        Story(
            id = "sohrab_tale",
            title = "داستان رستم و سهراب • تراژدی مهر و کین",
            subtitle = "روایت اشک‌بار رویارویی پدر و پسر در آوردگاه دژ سپید",
            chapter = "بخش دوم دفتر دوم • تراژدی سهراب",
            location = "مرز سمنگان و هیرمند",
            versesCount = "۳٬۵۰۰",
            readTime = "۴۵ دقیقه",
            excerpt = "روایت اشک‌بار رویارویی پدر و پسر در آوردگاه دژ سپید، نیرنگ افراسیاب و حسادت کی‌کاووس در دریغ داشتن نوش‌دارو پس از زخم کاری بر جگرگاه سهراب یل.",
            coupletLines = listOf(
                "«یکی داستان زد ز یزدان‌شناس / که راز جهان را ندارد هراس",
                "پدر با پسر در یکی رزمگاه / نبیند خرد جز دریغ و گناه»"
            ),
            imageUrl = ROSTAM_SOHRAB_URL,
            tags = listOf("تراژدی", "نوشدارو", "سمنگان"),
            statusBadge = "داستان شاخص شاهنامه • عصر کیانیان"
        ),
        Story(
            id = "haft_khan_tale",
            title = "هفت‌خوان رستم دستان و نابودی دیو سپید",
            subtitle = "سفر پرخطر تهمتن از دشت‌های ری تا بیشه‌زارهای مازندران",
            chapter = "دفتر پادشاهی کی‌کاووس",
            location = "زابل تا مازندران",
            versesCount = "۲٬۱۰۰",
            readTime = "۳۵ دقیقه",
            excerpt = "سفر پرخطر تهمتن از دشت‌های ری تا بیشه‌زارهای مازندران برای رهانیدن کی‌کاووس شاه و سپهبدان ایران از بند جادوی دیوان سیاه و کور شدن چشم سربازان.",
            coupletLines = listOf(
                "«بیامد به غار اندرون پهلوان / دلیر و جگرآور و پهلوان»",
                "«فروهشت شمشیر و ببرید پای / فغان و خروش آمد از خوابگاه»"
            ),
            imageUrl = BATTLE_SCENE_URL,
            tags = listOf("۷ منزلگاه حماسی", "رخش باوفا", "خوان هفتم"),
            isRead = true,
            statusBadge = "خوانده‌شده • نبرد هفتم تکمیل شد"
        ),
        Story(
            id = "siyavash_tale",
            title = "داستان سیاوش پاک‌زاد و آزمون آتش",
            subtitle = "گذر شاهزاده پرهیزگار سوار بر شبرنگ بهزاد از میان خرمن‌های آتشین",
            chapter = "دفتر حماسه سیاوش",
            location = "کاخ بلخ و توران",
            versesCount = "۴٬۲۰۰",
            readTime = "۵۰ دقیقه",
            excerpt = "گذر شاهزاده پرهیزگار سوار بر شبرنگ بهزاد از میان خرمن‌های آتشین برای اثبات پاک‌دامنی و هجرت اندوه‌بار به توران‌زمین و پی‌ریزی سیاوش‌گرد.",
            coupletLines = listOf(
                "«سیامک برفت و سیاوش بماند / خرد بر دل شهریاران نماند»"
            ),
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBVxveucs6TGiO1GcUafwSFmRoojhF94OYCVUxTrTwoDm37KMbq9ZlnNJ-FdZ0ErMRc3W2uC5xdWLGUDL1gaQr4THIL_U2STcKqVwJVJF5QwrlwrZWzunq27tsTAMu46-4H2q5MGDSDKc1eOZnD_GptsIqst6AyhzqWQEJ_S639ZXWUkpg-xkz7voNu7QdohH3V9N5qVaSDmU6N-wjvjPAb6Kbe66MNvsN3wT8DBihja8x8qkzFQnaY",
            tags = listOf("فرّ ایزدی و پاک‌دامنی", "سودابه", "آزمون آتش"),
            statusBadge = "پیشنهاد شاهنامه‌پژوهان"
        ),
        Story(
            id = "zal_simurgh_tale",
            title = "زال سپیدموی و پر سیمرغ البرز",
            subtitle = "رها شدن کودک سپیدموی سام نریمان در دره‌های سرد البرز",
            chapter = "دفتر پادشاهی منوچهر",
            location = "قله البرزکوه و زابلستان",
            versesCount = "۱٬۸۰۰",
            readTime = "۲۵ دقیقه",
            excerpt = "رها شدن کودک سپیدموی سام نریمان در دره‌های سرد البرز، پناه آوردن به بال‌های پرنیانی سیمرغ خردمند و بازگشت شکوهمند به دیار زابلستان.",
            coupletLines = listOf(
                "«بپرورد سیمرغ زال سپید / بدان کوهسار بلند امید»"
            ),
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBoEjMX_-rJXVfSP4Jip_wc0d2nmDMN3WJ8ehbyn3QyP7uQJIc9KHbbEJv09kQiueyLBVp5M_uzg_o3jzjcy_Cpj-CeVjeisTog-DOGs0AWZC6MCi6lAmDweaNNPYhk73_GMw8cQrNrLzToVB7DMa7DfOVC6u30_9Qcw9tegp7NsdqAxrOIhBwNeaaDm-lCE-VwkW1FLaUBzweUT6G4Vwwbg4zbFLWtXHXFYRFK2PFinE7zvkcGVrhz",
            tags = listOf("حکمت سیمرغ", "پیمان سام", "البرز"),
            statusBadge = "آغاز داستان‌های پهلوانی"
        ),
        Story(
            id = "bijan_manijeh_tale",
            title = "بیژن و منیژه در چاه افراسیاب",
            subtitle = "فریب گرگین میلاد، اسارت بیژن در چاه تاریک اکوان‌دیو",
            chapter = "دفتر پادشاهی کی‌خسرو",
            location = "مرز ارمن و توران",
            versesCount = "۲٬۶۰۰",
            readTime = "۳۲ دقیقه",
            excerpt = "فریب گرگین میلاد، اسارت بیژن در چاه تاریک اکوان‌دیو، پایمردی و وفاداری منیژه دختر تورانی و رخنه رستم در جامه بازرگانان برای رهایی او.",
            coupletLines = listOf(
                "«منیژه بیامد بدان تیره چاه / که بیند رخش تهمتن سپاه»"
            ),
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuA60Y3Yx4inoVkGyWrHIlZks3iGOWF31jD5odwpx5k4WixSfpelC932WvKao4kZZU4UEvRTC8rAkpSdqHUmWW1DI-krbywgNXy4gsBvwqiH1GF_TTCMQCkOn_IZ30Z3cSsXYnSBDzuVJ1OkjS6_U7Dqb96HY6UW_IIgH5MyP51cLyeJtxOJ-MityOZqDmFD5BZTC9cR8QewAz_JClSEpl13178Xxb0Zx4W_a4Zf3OJwV-qu1mt1ZShV",
            tags = listOf("سنگ اکوان دیو", "جام جهان‌بین", "عاشقانه و دلاوری"),
            statusBadge = "عاشقانه و دلاوری"
        )
    )

    // Curated thematic collections
    val thematicCollections: List<ThematicCollection> = listOf(
        ThematicCollection(
            id = "battles",
            title = "هفت‌خوان‌ها و نبردها",
            description = "پیکار با اژدها، جادوگران و دیوان سفید",
            countBadge = "۱۲ داستان",
            category = "swords"
        ),
        ThematicCollection(
            id = "elegies",
            title = "سوگ‌سروده‌ها",
            description = "سیاوش در آتش، مرگ فرود و سوگ اسفندیار",
            countBadge = "۸ سوگ‌نامه",
            category = "fire"
        ),
        ThematicCollection(
            id = "romance",
            title = "عاشقانه‌های کهن",
            description = "زال و رودابه سیمین‌تن، بیژن و منیژه",
            countBadge = "۶ منظومه",
            category = "heart"
        ),
        ThematicCollection(
            id = "justice",
            title = "پادشاهی و دادگری",
            description = "کیخسرو پرهیزگار، عهد فریدون و بوذرجمهر",
            countBadge = "۱۵ عهدنامه",
            category = "shield"
        )
    )

    // Mythic Map Waypoints
    val mapWaypoints: List<MythicWaypoint> = listOf(
        MythicWaypoint(
            id = "zabol",
            name = "زابلستان و نیمروز",
            label = "۱. زابلستان (مبدا حرکت)",
            xPercent = 0.22f,
            yPercent = 0.76f,
            khanNumber = "مبدا"
        ),
        MythicWaypoint(
            id = "nishapur",
            name = "بیشه نیشابور و کویر ریگ",
            label = "خوان ۱ و ۲ (شیر و بیابان)",
            xPercent = 0.44f,
            yPercent = 0.58f,
            khanNumber = "۱ و ۲"
        ),
        MythicWaypoint(
            id = "dragon_pass",
            name = "کوهسار اژدها و چشمه جادوان",
            label = "خوان ۳ و ۴ (اژدها و جادوگر)",
            xPercent = 0.55f,
            yPercent = 0.45f,
            khanNumber = "۳ و ۴"
        ),
        MythicWaypoint(
            id = "esporoz_cave",
            name = "غار اسپورز در مازندران",
            label = "غار دیو سپید (فعال)",
            xPercent = 0.68f,
            yPercent = 0.30f,
            khanNumber = "۷",
            isSelected = true
        )
    )

    // Battle skills for Rostam vs Div-e Sepid
    val battleSkills: List<BattleSkill> = listOf(
        BattleSkill(
            id = "sword",
            name = "تیغ گیتی‌فروز",
            cost = 30,
            damage = 180,
            description = "وارد کردن ضربت کاری و مهلک با شمشیر آبگون"
        ),
        BattleSkill(
            id = "lasso",
            name = "کمند کیانی",
            cost = 20,
            damage = 60,
            description = "به بند کشیدن دست و پای دیو و شکستن مقاومت"
        ),
        BattleSkill(
            id = "rakhsh",
            name = "خروش رخش",
            cost = 25,
            damage = 90,
            description = "حمله غافلگیرانه و شکستن تمرکز دیو با شیهه رخش"
        ),
        BattleSkill(
            id = "shield",
            name = "سپر ببر بیان",
            cost = 15,
            damage = 0,
            heal = 120,
            description = "دفع ۷۵٪ آسیب و بازیابی ۱۲۰ واحد نیرو"
        )
    )
}
