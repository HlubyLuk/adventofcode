/**
 * 
 */
package cz.hlubyluk.adventofcode.event2015.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cz.hlubyluk.adventofcode.IDay;

/**
 * https://adventofcode.com/2015/day/5
 *
 * @author HlubyLuk
 */
public interface IE15D05 extends IDay {
  String INPUT_TEST_1 = "ugknbfddgicrmopn";
  String INPUT_TEST_2 = "haegwjzuvuyypxyu";
  String INPUT_TEST_3 = "jchzalrnumimnmhp";
  String INPUT_TEST_4 = "dvszwmarrgswjxmb";
  String INPUT_TEST_5 = "aaa";

  static class Parser {
    public Parser() {
    }

    public List<NiceString> parseInput() {
      List<NiceString> list = new ArrayList<>();

      Scanner sc = new Scanner(IE15D05.INPUT);
      while (sc.hasNextLine()) {
        list.add(new NiceString(sc.nextLine()));
      }
      sc.close();

      return list;
    }

    public List<NiceString> parseTest() {
      List<NiceString> list = new ArrayList<>();
      list.add(new NiceString(INPUT_TEST_1));
      list.add(new NiceString(INPUT_TEST_2));
      list.add(new NiceString(INPUT_TEST_3));
      list.add(new NiceString(INPUT_TEST_4));
      list.add(new NiceString(INPUT_TEST_5));

      return list;
    }
  }

  static class NiceString {
    private final String line;
    private static final char[] VOWELS = { 'a', 'e', 'i', 'o', 'u' };
    private static final String[] RESTRICTIONS = { "ab", "cd", "pq", "xy" };

    /**
     * Constructor.
     *
     * @param line from file.
     */
    public NiceString(String line) {
      this.line = line;
    }

    private int countVowels() {
      int count = 0;

      for (char c : this.line.toCharArray()) {
        for (char v : NiceString.VOWELS) {
          if (c == v) {
            count += 1;
          }
        }
      }

      return count;
    }

    private int countTwiceCharacters() {
      int count = 0;

      char[] characters = this.line.toCharArray();
      for (int i = 1; i < characters.length; i += 1) {
        if (characters[i - 1] == characters[i]) {
          count += 1;
        }
      }

      return count;
    }

    private int countRestrictions() {
      int count = 0;

      for (String restriction : NiceString.RESTRICTIONS) {
        if (this.line.contains(restriction)) {
          count += 1;
        }
      }

      return count;
    }

    public boolean analyze() {
      return this.countVowels() >= 3 && this.countTwiceCharacters() >= 1 && this.countRestrictions() == 0;
    }
  }

  String INPUT = "uxcplgxnkwbdwhrp\n" + "suerykeptdsutidb\n" + "dmrtgdkaimrrwmej\n" + "ztxhjwllrckhakut\n"
      + "gdnzurjbbwmgayrg\n" + "gjdzbtrcxwprtery\n" + "fbuqqaatackrvemm\n" + "pcjhsshoveaodyko\n" + "lrpprussbesniilv\n"
      + "mmsebhtqqjiqrusd\n" + "vumllmrrdjgktmnb\n" + "ptsqjcfbmgwdywgi\n" + "mmppavyjgcfebgpl\n" + "zexyxksqrqyonhui\n"
      + "npulalteaztqqnrl\n" + "mscqpccetkktaknl\n" + "ydssjjlfejdxrztr\n" + "jdygsbqimbxljuue\n" + "ortsthjkmlonvgci\n"
      + "jfjhsbxeorhgmstc\n" + "vdrqdpojfuubjbbg\n" + "xxxddetvrlpzsfpq\n" + "zpjxvrmaorjpwegy\n" + "laxrlkntrukjcswz\n"
      + "pbqoungonelthcke\n" + "niexeyzvrtrlgfzw\n" + "zuetendekblknqng\n" + "lyazavyoweyuvfye\n" + "tegbldtkagfwlerf\n"
      + "xckozymymezzarpy\n" + "ehydpjavmncegzfn\n" + "jlnespnckgwmkkry\n" + "bfyetscttekoodio\n" + "bnokwopzvsozsbmj\n"
      + "qpqjhzdbuhrxsipy\n" + "vveroinquypehnnk\n" + "ykjtxscefztrmnen\n" + "vxlbxagsmsuuchod\n" + "punnnfyyufkpqilx\n"
      + "zibnnszmrmtissww\n" + "cxoaaphylmlyljjz\n" + "zpcmkcftuuesvsqw\n" + "wcqeqynmbbarahtz\n" + "kspontxsclmbkequ\n"
      + "jeomqzucrjxtypwl\n" + "ixynwoxupzybroij\n" + "ionndmdwpofvjnnq\n" + "tycxecjvaxyovrvu\n" + "uxdapggxzmbwrity\n"
      + "csskdqivjcdsnhpe\n" + "otflgdbzevmzkxzx\n" + "verykrivwbrmocta\n" + "ccbdeemfnmtputjw\n" + "suyuuthfhlysdmhr\n"
      + "aigzoaozaginuxcm\n" + "ycxfnrjnrcubbmzs\n" + "fgbqhrypnrpiizyy\n" + "taoxrnwdhsehywze\n" + "echfzdbnphlwjlew\n"
      + "jhmomnrbfaawicda\n" + "fywndkvhbzxxaihx\n" + "aftuyacfkdzzzpem\n" + "yytzxsvwztlcljvb\n" + "iblbjiotoabgnvld\n"
      + "kvpwzvwrsmvtdxcx\n" + "ardgckwkftcefunk\n" + "oqtivsqhcgrcmbbd\n" + "wkaieqxdoajyvaso\n" + "rkemicdsrtxsydvl\n"
      + "sobljmgiahyqbirc\n" + "pbhvtrxajxisuivj\n" + "ggqywcbfckburdrr\n" + "gmegczjawxtsywwq\n" + "kgjhlwyonwhojyvq\n"
      + "bpqlmxtarjthtjpn\n" + "pxfnnuyacdxyfclr\n" + "isdbibbtrqdfuopn\n" + "vucsgcviofwtdjcg\n" + "ywehopujowckggkg\n"
      + "mzogxlhldvxytsgl\n" + "mllyabngqmzfcubp\n" + "uwvmejelibobdbug\n" + "brebtoppnwawcmxa\n" + "fcftkhghbnznafie\n"
      + "sqiizvgijmddvxxz\n" + "qzvvjaonnxszeuar\n" + "abekxzbqttczywvy\n" + "bkldqqioyhrgzgjs\n" + "lilslxsibyunueff\n"
      + "ktxxltqgfrnscxnx\n" + "iwdqtlipxoubonrg\n" + "twncehkxkhouoctj\n" + "bdwlmbahtqtkduxz\n" + "smbzkuoikcyiulxq\n"
      + "bjmsdkqcmnidxjsr\n" + "icbrswapzdlzdanh\n" + "eyszxnhbjziiplgn\n" + "pdxhrkcbhzqditwb\n" + "nfulnpvtzimbzsze\n"
      + "glayzfymwffmlwhk\n" + "bejxesxdnwdlpeup\n" + "ukssntwuqvhmsgwj\n" + "hoccqxlxuuoomwyc\n" + "rapztrdfxrosxcig\n"
      + "cxowzhgmzerttdfq\n" + "yzhcurqhdxhmolak\n" + "kqgulndpxbwxesxi\n" + "yjkgcvtytkitvxiu\n" + "xnhfqhnnaceaqyue\n"
      + "qkuqreghngfndifr\n" + "xesxgeaucmhswnex\n" + "occbvembjeuthryi\n" + "dmefxmxqjncirdwj\n" + "ystmvxklmcdlsvin\n"
      + "pplykqlxmkdrmydq\n" + "cbbjkpbdvjhkxnuc\n" + "embhffzsciklnxrz\n" + "asrsxtvsdnuhcnco\n" + "xcbcrtcnzqedktpi\n"
      + "mglwujflcnixbkvn\n" + "mnurwhkzynhahbjp\n" + "cekjbablkjehixtj\n" + "kbkcmjhhipcjcwru\n" + "usifwcsfknoviasj\n"
      + "rsfgocseyeflqhku\n" + "prgcyqrickecxlhm\n" + "asbawplieizkavmq\n" + "sylnsirtrxgrcono\n" + "nzspjfovbtfkloya\n"
      + "qfxmsprfytvaxgtr\n" + "yckpentqodgzngnv\n" + "ycsfscegcexcnbwq\n" + "kbmltycafudieyuh\n" + "tpahmvkftilypxuf\n"
      + "qivqozjrmguypuxu\n" + "gdhbfradjuidunbk\n" + "vxqevjncsqqnhmkl\n" + "rpricegggcfeihst\n" + "xucvzpprwtdpzifq\n"
      + "egyjcyyrrdnyhxoo\n" + "kfbrzmbtrrwyeofp\n" + "qpjdsocrtwzpjdkd\n" + "reboldkprsgmmbit\n" + "vwkrzqvvhqkensuy\n"
      + "ydvmssepskzzvfdp\n" + "vqbigplejygdijuu\n" + "mzpgnahrhxgjriqm\n" + "uiejixjadpfsxqcv\n" + "tosatnvnfjkqiaha\n"
      + "yipuojpxfqnltclx\n" + "pcxwvgcghfpptjlf\n" + "shrudjvvapohziaj\n" + "jdckfjdtjsszdzhj\n" + "hgisfhcbdgvxuilk\n"
      + "gytnfjmrfujnmnpp\n" + "ohflkgffnxmpwrrs\n" + "jzxajbkwwjknasjh\n" + "xrcxfollmejrislv\n" + "djjlwykouhyfukob\n"
      + "rittommltkbtsequ\n" + "lpbvkxdcnlikwcxm\n" + "vkcrjmcifhwgfpdj\n" + "dkhjqwtggdrmcslq\n" + "swnohthfvjvoasvt\n"
      + "yrzoksmcnsagatii\n" + "duommjnueqmdxftp\n" + "inlvzlppdlgfmvmx\n" + "xibilzssabuqihtq\n" + "inkmwnvrkootrged\n"
      + "ldfianvyugqtemax\n" + "gbvwtiexcuvtngti\n" + "temjkvgnwxrhdidc\n" + "askbbywyyykerghp\n" + "onezejkuwmrqdkfr\n"
      + "kybekxtgartuurbq\n" + "ubzjotlasrewbbkl\n" + "stueymlsovqgmwkh\n" + "lhduseycrewwponi\n" + "yohdmucunrgemqcu\n"
      + "onnfbxcuhbuifbyc\n" + "odrjkigbrsojlqbt\n" + "imqkqqlkgmttpxtx\n" + "sxmlkspqoluidnxw\n" + "akaauujpxhnccleb\n"
      + "xvgpghhdtpgvefnk\n" + "jdxeqxzsbqtvgvcq\n" + "mdusenpygmerxnni\n" + "agihtqvgkmgcbtaw\n" + "dovxcywlyvspixad\n"
      + "uulgazeyvgtxqkfz\n" + "ndhmvrwuflhktzyo\n" + "hcaqkmrbvozaanvm\n" + "tvfozbqavqxdqwqv\n" + "rlkpycdzopitfbsv\n"
      + "dmyjtmjbtnvnedhs\n" + "fmwmqeigbzrxjvdu\n" + "twgookcelrjmczqi\n" + "grxosmxvzgymjdtz\n" + "zsstljhzugqybueo\n"
      + "jpeapxlytnycekbd\n" + "iasykpefrwxrlvxl\n" + "azohkkqybcnsddus\n" + "aoaekngakjsgsonx\n" + "awsqaoswqejanotc\n"
      + "sgdxmketnjmjxxcp\n" + "ylnyuloaukdrhwuy\n" + "ewoqjmakifbefdib\n" + "ytjfubnexoxuevbp\n" + "ewlreawvddptezdd\n"
      + "vmkonztwnfgssdog\n" + "ahbpuqygcwmudyxn\n" + "kmahpxfjximorkrh\n" + "otjbexwssgpnpccn\n" + "aewskyipyztvskkl\n"
      + "urqmlaiqyfqpizje\n" + "nrfrbedthzymfgfa\n" + "vndwwrjrwzoltfgi\n" + "iiewevdzbortcwwe\n" + "qiblninjkrkhzxgi\n"
      + "xmvaxqruyzesifuu\n" + "yewuzizdaucycsko\n" + "hmasezegrhycbucy\n" + "dwpjrmkhsmnecill\n" + "hnffpbodtxprlhss\n"
      + "avmrgrwahpsvzuhm\n" + "nksvvaswujiukzxk\n" + "zzzapwhtffilxphu\n" + "vwegwyjkbzsrtnol\n" + "qurpszehmkfqwaok\n"
      + "iknoqtovqowthpno\n" + "brlmpjviuiagymek\n" + "efxebhputzeulthq\n" + "mzkquarxlhlvvost\n" + "xsigcagzqbhwwgps\n"
      + "qufztljyzjxgahdp\n" + "dlfkavnhobssfxvx\n" + "hgdpcgqxjegnhjlr\n" + "fboomzcvvqudjfbi\n" + "wnjuuiivaxynqhrd\n"
      + "nhcgzmpujgwisguw\n" + "wjeiacxuymuhykgk\n" + "qmeebvxijcgdlzpf\n" + "nmmnxsehhgsgoich\n" + "ejluaraxythbqfkl\n"
      + "mdbsbwnaypvlatcj\n" + "nnfshfibmvfqrbka\n" + "dvckdmihzamgqpxr\n" + "foztgqrjbwyxvewk\n" + "okpryqcbvorcxhoh\n"
      + "fpiwsndulvtthctx\n" + "zrbiovlmzdmibsiq\n" + "setwafbnnzcftutg\n" + "nyvqghxhgkxfobdm\n" + "enpvqadzarauhajl\n"
      + "twblhpvkazpdmhmr\n" + "lbhlllsgswvhdesh\n" + "tdfwkgxnqjxcvsuo\n" + "lnvyjjbwycjbvrrb\n" + "jsxqdvmzaydbwekg\n"
      + "xirbcbvwlcptuvoa\n" + "hwnukxenilatlfsk\n" + "khwopjqkxprgopmd\n" + "sljzdoviweameskw\n" + "stkrdmxmpaijximn\n"
      + "fdilorryzhmeqwkc\n" + "mfchaaialgvoozra\n" + "gjxhoxeqgkbknmze\n" + "beowovcoqnginrno\n" + "mkgmsgwkwhizunxo\n"
      + "phnhfusyoylvjdou\n" + "csehdlcmwepcpzmq\n" + "pgojomirzntgzohj\n" + "fkffgyfsvwqhmboz\n" + "mrvduasiytbzfwdn\n"
      + "epzrmsifpmfaewng\n" + "ooqxnoyqrlozbbyf\n" + "ahcxfmgtedywrbnx\n" + "ibqktvqmgnirqjot\n" + "xarssauvofdiaefn\n"
      + "xradvurskwbfzrnw\n" + "nxklmulddqcmewad\n" + "twichytatzoggchg\n" + "qmgvroqwrjgcycyv\n" + "yvezgulgrtgvyjjm\n"
      + "jgmcklzjdmznmuqk\n" + "bytajdwwconasjzt\n" + "apjttucpycyghqhu\n" + "flfejjzihodwtyup\n" + "gmrtrwyewucyqotv\n"
      + "nlohdrlymbkoenyl\n" + "wxcmqwbrwgtmkyfe\n" + "njtzlceyevmisxfn\n" + "htbbidsfbbshmzlt\n" + "gxhjeypjwghnrbsf\n"
      + "cifcwnbtazronikv\n" + "ezvjijcjcyszwdjy\n" + "srffeyrvyetbecmc\n" + "xpjefrtatrlkbkzl\n" + "yhncvfqjcyhsxhbb\n"
      + "pqhcufzlcezhihpr\n" + "qtdsfvxfqmsnzisp\n" + "dfonzdicxxhzxkrx\n" + "mqqqzhxkyfpofzty\n" + "dodjadoqyxsuazxt\n"
      + "jjwkrlquazzjbvlm\n" + "ttosfloajukoytfb\n" + "llateudmzxrzbqph\n" + "criqihrysgesmpsx\n" + "npszvlittbcxxknj\n"
      + "qmzojrvraitrktil\n" + "cfyoozzpwxwkwoto\n" + "daxohtcgvtktggfw\n" + "vthkpkoxmiuotjaj\n" + "pkfkyobvzjeecnui\n"
      + "ojcjiqrfltbhcdze\n" + "scbivhpvjkjbauun\n" + "ysowvwtzmqpjfwyp\n" + "laeplxlunwkfeaou\n" + "jufhcikovykwjhsa\n"
      + "xrucychehzksoitr\n" + "pyaulaltjkktlfkq\n" + "oypfrblfdhwvqxcv\n" + "zybrgxixvhchgzcf\n" + "puoagefcmlxelvlp\n"
      + "xjnhfdrsbhszfsso\n" + "ocgvzryoydaoracw\n" + "bxpnqllmptkpeena\n" + "pziyeihxlxbbgdio\n" + "bvtrhtlbfzmglsfc\n"
      + "ggpuvtseebylsrfk\n" + "pukenexjqecnivfj\n" + "jswabfbzpnhhdbpn\n" + "enojrtwqpfziyqsv\n" + "rjtmxudgcudefuiz\n"
      + "iqmjxynvtvdacffc\n" + "uheywxlsusklitvl\n" + "kwhxduejafdpmqdc\n" + "rspgblenbqlmcltn\n" + "rczhurnrqqgjutox\n"
      + "dqhytibjzxkdblzl\n" + "hpbieadydiycvfys\n" + "pucztfoqvenxiuym\n" + "nqpfzgpblwijiprf\n" + "ltgseeblgajbvltk\n"
      + "mwxukbsnapewhfrc\n" + "dvxluiflicdtnxix\n" + "pexfbpgnqiqymxcq\n" + "dakudfjjwtpxuzxy\n" + "letlceyzlgmnrewu\n"
      + "ojktahbsdifdfhmd\n" + "anezoybbghjudbih\n" + "sawxtlvzysaqkbbf\n" + "ttnkctcevpjiwqua\n" + "edrwrdvbaoqraejd\n"
      + "wnbfilvuienjxlcr\n" + "wqhzwvyybyxhhtsm\n" + "jxbgvyaqczwdlxfo\n" + "wbypqfmbwrsvfmdv\n" + "izdxjyfpidehbets\n"
      + "vbxbggqseurknjor\n" + "egpmpoxickhvwdlz\n" + "ivfrzklvpwoemxsy\n" + "xkziseheibmrpdww\n" + "xnrmtoihaudozksa\n"
      + "efemdmbxdsaymlrw\n" + "yjdjeckmsrckaagx\n" + "vlftqxxcburxnohv\n" + "fwyquwgajaxebduj\n" + "dwpmqvcxqwwnfkkr\n"
      + "isduxxjfsluuvwga\n" + "avdtdppodpntojgf\n" + "vrcoekdnutbnlgqk\n" + "kbhboxjmgomizxkl\n" + "cgsfpjrmewexgzfy\n"
      + "usdtnhjxbvtnafvp\n" + "bjoddgxbuxzhnsqd\n" + "hoyqdzofddedevsb\n" + "rwiwbvqfjajotaoj\n" + "iabomphsuyfptoos\n"
      + "bubeonwbukprpvhy\n" + "xurgunofmluhisxm\n" + "puyojzdvhktawkua\n" + "dbvqhztzdsncrxkb\n" + "oaeclqzyshuuryvm\n"
      + "nmgwfssnflxvcupr\n" + "vjkiwbpunkahtsrw\n" + "romyflhrarxchmyo\n" + "yecssfmetezchwjc\n" + "qwtocacqdslhozkd\n"
      + "mesexvfbtypblmam\n" + "mtjucgtjesjppdtt\n" + "pvodhqqoeecjsvwi\n" + "vvlcwignechiqvxj\n" + "wiqmzmmjgjajwgov\n"
      + "kwneobiiaixhclev\n" + "lkdeglzrrxuomsyt\n" + "oqovuwcpwbghurva\n" + "lfsdcxsasmuarwwg\n" + "awkbafhswnfbhvck\n"
      + "sztxlnmyvqsiwljg\n" + "hozxgyxbcxjzedvs\n" + "oifkqgfqmflxvyzn\n" + "mfvnehsajlofepib\n" + "delgbyfhsyhmyrfa\n"
      + "uenimmwriihxoydv\n" + "vjqutpilsztquutn\n" + "kfebsaixycrodhvl\n" + "coifyqfwzlovrpaj\n" + "xiyvdxtkqhcqfsqr\n"
      + "hoidcbzsauirpkyt\n" + "fiumhfaazfkbaglq\n" + "fzwdormfbtkdjgfm\n" + "faxqrortjdeihjfv\n" + "ljhaszjklhkjvrfi\n"
      + "pzrxsffkuockoqyl\n" + "immbtokjmwyrktzn\n" + "lzgjhyiywwnuxpfx\n" + "vhkocmwzkfwjuzog\n" + "ghntjkszahmdzfbl\n"
      + "gbcthxesvqbmzggy\n" + "oyttamhpquflojkh\n" + "nbscpfjwzylkfbtv\n" + "wnumxzqbltvxtbzs\n" + "jfhobjxionolnouc\n"
      + "nrtxxmvqjhasigvm\n" + "hweodfomsnlgaxnj\n" + "lfgehftptlfyvvaj\n" + "ccoueqkocrdgwlvy\n" + "euhgvirhsaotuhgf\n"
      + "pdlsanvgitjvedhd\n" + "seokvlbhrfhswanv\n" + "pntdqaturewqczti\n" + "jkktayepxcifyurj\n" + "dhzzbiaisozqhown\n"
      + "wehtwakcmqwczpbu\n" + "zwvozvspqmuckkcd\n" + "efucjlrwxuhmjubr\n" + "lzodaxuyntrnxwvp\n" + "qdezfvpyowfpmtwd\n"
      + "mizijorwrkanesva\n" + "txmitbiqoiryxhpz\n" + "xhsqgobpouwnlvps\n" + "muixgprsknlqaele\n" + "disgutskxwplodra\n"
      + "bmztllsugzsqefrm\n" + "ymwznyowpaaefkhm\n" + "ebfifzloswvoagqh\n" + "pkldomvvklefcicw\n" + "ziqzbbfunmcgrbtq\n"
      + "iuekfpbkraiwqkic\n" + "jflgjidirjapcuqo\n" + "achsfbroyrnqnecg\n" + "udbhouhlgjjzapzr\n" + "arerrohyhhkmwhyo\n"
      + "txyjzkqexgvzdtow\n" + "ogzrjwibvzoucrpg\n" + "rfdftaesxdnghwhd\n" + "axdhwmpuxelmpabo\n" + "gtktemowbsvognac\n"
      + "wkfuclilhqjzxztk\n" + "qbwjouutzegaxhrz\n" + "opfziwqqbwhzzqhj\n" + "pvcvcsupfwsmeacs\n" + "xsbohvbguzsgpawn\n"
      + "sczoefukwywxriwj\n" + "oqkhcqfdeaifbqoc\n" + "vtsrholxbjkhwoln\n" + "yuvapljnwbssfbhi\n" + "dxdfwccqvyzeszyl\n"
      + "gdbmjtonbiugitmb\n" + "qunirtqbubxalmxr\n" + "zzxsirhdaippnopr\n" + "fibtndkqjfechbmq\n" + "gqgqyjvqmfiwiyio\n"
      + "ihwsfkwhtzuydlzw\n" + "eygyuffeyrbbhlit\n" + "zdlsaweqomzrhdyy\n" + "ptbgfzuvxiuuxyds\n" + "llxlfdquvovzuqva\n"
      + "wfrltggyztqtyljv\n" + "kwipfevnbralidbm\n" + "gbhqfbrvuseellbx\n" + "obkbuualrzrakknv\n" + "hlradjrwyjgfqugu\n"
      + "vtqlxbyiaiorzdsp\n" + "tedcbqoxsmbfjeyy\n" + "cxdppfvklbdayghy\n" + "gjnofexywmdtgeft\n" + "ldzeimbbjmgpgeax\n"
      + "egrwsmshbvbawvja\n" + "vadfrjvcrdlonrkg\n" + "mojorplakzfmzvtp\n" + "jyurlsoxhubferpo\n" + "ijwqogivvzpbegkm\n"
      + "cnmetoionfxlutzg\n" + "lawigelyhegqtyil\n" + "mqosapvnduocctcd\n" + "eqncubmywvxgpfld\n" + "vigfretuzppxkrfy\n"
      + "ncwynsziydoflllq\n" + "cbllqinsipfknabg\n" + "ndtbvdivzlnafziq\n" + "iqrrzgzntjquzlrs\n" + "damkuheynobqvusp\n"
      + "jxctymifsqilyoxa\n" + "ylritbpusymysmrf\n" + "paoqcuihyooaghfu\n" + "obhpkdaibwixeepl\n" + "igrmhawvctyfjfhd\n"
      + "ybekishyztlahopt\n" + "vkbniafnlfqhhsrq\n" + "kltdigxmbhazrywf\n" + "ufhcoyvvxqzeixpr\n" + "klcxdcoglwmeynjt\n"
      + "funpjuvfbzcgdhgs\n" + "akgyvyfzcpmepiuc\n" + "zhlkgvhmjhwrfmua\n" + "ibsowtbnrsnxexuz\n" + "vpufbqilksypwlrn\n"
      + "ngrintxhusvdkfib\n" + "ziuwswlbrxcxqslw\n" + "sucledgxruugrnic\n" + "zwnsfsyotmlpinew\n" + "oaekskxfcwwuzkor\n"
      + "qjmqwaktpzhwfldu\n" + "tmgfgqgpxaryktxo\n" + "qfaizepgauqxvffk\n" + "addkqofusrstpamf\n" + "shdnwnnderkemcts\n"
      + "gwfygbsugzptvena\n" + "fpziernelahopdsj\n" + "bkkrqbsjvyjtqfax\n" + "gxrljlqwxghbgjox\n" + "ipfwnqaskupkmevm\n"
      + "nnyoyhnqyfydqpno\n" + "lgzltbrrzeqqtydq\n" + "fgzxqurhtdfucheb\n" + "jvpthtudlsoivdwj\n" + "bmlhymalgvehvxys\n"
      + "fhklibetnvghlgnp\n" + "hfcyhptxzvblvlst\n" + "donanindroexgrha\n" + "oqawfmslbgjqimzx\n" + "jzgehjfjukizosep\n"
      + "bhlgamcjqijpvipb\n" + "jrcrdjrvsyxzidsk\n" + "ouwfwwjqezkofqck\n" + "wrvsbnkhyzayialf\n" + "knhivfqjxrxnafdl\n"
      + "hbxbgqsqwzijlngf\n" + "qlffukpfmnxpfiyq\n" + "evhxlouocemdkwgk\n" + "baxhdrmhaukpmatw\n" + "nwlyytsvreqaminp\n"
      + "ljsjjzmlsilvxgal\n" + "onunatwxfzwlmgpk\n" + "njgolfwndqnwdqde\n" + "ngdgcjzxupkzzbqi\n" + "ieawycvvmvftbikq\n"
      + "ccyvnexuvczvtrit\n" + "enndfwjpwjyasjvv\n" + "tcihprzwzftaioqu\n" + "bkztdkbrxfvfeddu\n" + "qkvhtltdrmryzdco\n"
      + "rurtxgibkeaibofs\n" + "mjxypgscrqiglzbp\n" + "unpkojewduprmymd\n" + "csqtkhjxpbzbnqog\n" + "mednhjgbwzlhmufi\n"
      + "sfrwfazygygzirwd\n" + "ijqeupbrhhpqxota\n" + "cmhpncanwudyysyh\n" + "wwcxbwzrplfzrwxd\n" + "jriomldifuobjpmq\n"
      + "radonyagpulnnyee\n" + "ryqjwxsspbbhnptd\n" + "yeoqpnsdhludlmzf\n" + "qsqlkeetyalenueh\n" + "qnnedenwsjdrcrzt\n"
      + "lejkuhsllxbhfcrx\n" + "anddbvllrrqefvke\n" + "wdtljquijaksvdsv\n" + "adslgvfuqqdkzvbc\n" + "whbccefjpcnjwhaq\n"
      + "kqrfuankaibohqsg\n" + "fyxisfwihvylgnfd\n" + "rwqdrddghyqudcif\n" + "syhzowthaaiiouaf\n" + "zjmrtgrnohxmtidu\n"
      + "deecwkfvjffxrzge\n" + "dztmvolqxkhdscxe\n" + "cdghcrgavygojhqn\n" + "pepqmdbjhnbugqeu\n" + "pnumdjpnddbxhieg\n"
      + "jzfhxeyahiagizfw\n" + "hdkwugrhcniueyor\n" + "gmgudeqlbmqynflu\n" + "toidiotdmfkxbzvm\n" + "pyymuoevoezlfkjb\n"
      + "etrbwuafvteqynlr\n" + "usvytbytsecnmqtd\n" + "dfmlizboawrhmvim\n" + "vrbtuxvzzefedlvs\n" + "vslcwudvasvxbnje\n"
      + "xdxyvoxaubtwjoif\n" + "mduhzhascirittdf\n" + "cqoqdhdxgvvvxamk\n" + "dshnfwhqjbhuznqr\n" + "zimthfxbdmkulkjg\n"
      + "luylgfmmwbptyzpj\n" + "iujpcgogshhotqrc\n" + "caqcyzqcumfljvsp\n" + "sprtitjlbfpygxya\n" + "fnconnrtnigkpykt\n"
      + "irmqaqzjexdtnaph\n" + "bbqrtoblmltvwome\n" + "ozjkzjfgnkhafbye\n" + "hwljjxpxziqbojlw\n" + "zahvyqyoqnqjlieb\n"
      + "dptshrgpbgusyqsc\n" + "uzlbnrwetkbkjnlm\n" + "yccaifzmvbvwxlcc\n" + "wilnbebdshcrrnuu\n" + "evxnoebteifbffuq\n"
      + "khbajekbyldddzfo\n" + "kjivdcafcyvnkojr\n" + "wtskbixasmakxxnv\n" + "uzmivodqzqupqkwx\n" + "rxexcbwhiywwwwnu\n"
      + "rowcapqaxjzcxwqi\n" + "fkeytjyipaxwcbqn\n" + "pyfbntonlrunkgvq\n" + "qiijveatlnplaifi\n" + "ltnhlialynlafknw\n"
      + "urrhfpxmpjwotvdn\n" + "xklumhfyehnqssys\n" + "civrvydypynjdoap\n" + "fvbmxnfogscbbnyd\n" + "oznavyflpzzucuvg\n"
      + "iyshrpypfbirahqo\n" + "qmzbfgelvpxvqecy\n" + "xkkxaufomsjbofmk\n" + "irlouftdmpitwvlq\n" + "csjoptbdorqxhnjg\n"
      + "bkryeshfsaqpdztm\n" + "guxbdqzfafsjoadl\n" + "tgrltexgrzatzwxf\n" + "cwsgsijqdanubxad\n" + "xafnexgturwrzyrg\n"
      + "apcrsqdbsbaxocxr\n" + "pspgxnzcevmvvejk\n" + "szephmeegvegugdt\n" + "ndjsoloeacasxjap\n" + "bdnfksliscnirjfu\n"
      + "ehglacmzpcgglpux\n" + "jwweijomqfcupvzw\n" + "yesblmmkqhbazmdu\n" + "sjsmalypmuslzgac\n" + "fkiqatyttlnuhdho\n"
      + "tlhnyuzdocvfdihq\n" + "ngehtjmycevnybga\n" + "obxodzcdgtrycgry\n" + "stkyrvdfbwovawmk\n" + "bdkhqcfrqaxhxloo\n"
      + "gpvumnuoiozipnrk\n" + "jbhanddinpqhxeol\n" + "hwkzkmbmsrvunzit\n" + "rfuomegkxbyamjpw\n" + "yzbljuksletipzwm\n"
      + "eafedkagwitzqigl\n" + "prenqvsbotqckgwy\n" + "spedpbwzphdrfxfz\n" + "cmsuqwemhwixkxet\n" + "xgdyeqbqfldvaccq\n"
      + "eooxgsrfsbdaolja\n" + "kyhqylxooewrhkho\n" + "mswieugqpoefmspt\n" + "uszoqundysdyeqlc\n" + "hkmjdggxefdyykbq\n"
      + "dtuhjnlaliodtlvh\n" + "oalbueqbhpxoxvvx\n" + "oowxtxsoqdwhzbya\n" + "lclajfsrpmtwvzkm\n" + "fxmjufpqtpyazeqo\n"
      + "ozlmreegxhfwwwmf\n" + "mqzrajxtxbaemrho\n" + "nfglecsyqduhakjr\n" + "nkxqtmasjjkpkqbp\n" + "jjfonbqimybvzeus\n"
      + "vjqkhkhjlmvpwkud\n" + "wxxhnvfhetsamzjr\n" + "pladhajujzttgmsw\n" + "dbycgxeymodsdlhm\n" + "qxszeuaahuoxjvwu\n"
      + "adultomodzrljxve\n" + "dmhgrbhvvpxyzwdn\n" + "slohrlwxerpahtyp\n" + "mngbocwyqrsrrxdb\n" + "facyrtflgowfvfui\n"
      + "hyvazpjucgghmmxh\n" + "twtrvjtncmewcxit\n" + "uejkrpvilgccfpfr\n" + "psqvolfagjfvqkum\n" + "nvzolslmiyavugpp\n"
      + "lpjfutvtwbddtqiu\n" + "fkjnfcdorlugmcha\n" + "eaplrvdckbcqqvhq\n" + "xrcydhkockycburw\n" + "iswmarpwcazimqxn\n"
      + "kicnnkjdppitjwrl\n" + "vwywaekzxtmeqrsu\n" + "dxlgesstmqaxtjta\n" + "pmeljgpkykcbujbb\n" + "vhpknqzhgnkyeosz\n"
      + "jprqitpjbxkqqzmz\n" + "fiprxgsqdfymyzdl\n" + "dzvfwvhfjqqsifga\n" + "aeakhfalplltmgui\n" + "frqrchzvenhozzsu\n"
      + "hsvikeyewfhsdbmy\n" + "puedjjhvxayiwgvg\n" + "zmsonnclfovjoewb\n" + "bnirelcaetdyaumi\n" + "szvudroxhcitatvf\n"
      + "sccfweuyadvrjpys\n" + "yiouqrnjzsdwyhwa\n" + "xyjhkqbnfmjjdefz\n" + "fjwgemkfvettucvg\n" + "aapqpwapzyjnusnr\n"
      + "dytxpkvgmapdamtc\n" + "hgocpfoxlheqpumw\n" + "twzuiewwxwadkegg\n" + "qdbosnhyqmyollqy\n" + "fclbrlkowkzzitod\n"
      + "sgxnrrpwhtkjdjth\n" + "xckvsnkvnvupmirv\n" + "nioicfeudrjzgoas\n" + "lcemtyohztpurwtf\n" + "oyjxhhbswvzekiqn\n"
      + "idkblbyjrohxybob\n" + "rthvloudwmktwlwh\n" + "oyzhmirzrnoytaty\n" + "ysdfhuyenpktwtks\n" + "wxfisawdtbpsmwli\n"
      + "vgmypwlezbmzeduk\n" + "rpepcfpelvhzzxzj\n" + "zxbovsmixfvmamnj\n" + "cpkabmaahbnlrhiz\n" + "jvomcbqeoqrmynjj\n"
      + "iqdeisnegnkrkdws\n" + "ilhemlrtxdsdnirr\n" + "fjimtscrwbfuwmpo\n" + "lmfiylebtzwtztmx\n" + "ddouhysvomrkcpgu\n"
      + "xtjwvzdhgnwwauwi\n" + "cntzuwcumbsebwyy\n" + "hieqvdlvnxkygeda\n" + "hushfszxskjdrjxi\n" + "xvdfzqblccfoxvyq\n"
      + "nldnrtieteunyxnb\n" + "vszpidfocenlhzqb\n" + "ofcuvtwhortxesoq\n" + "bwniqemqwxlejcfq\n" + "wkqiwdjnytjnomps\n"
      + "rbadoommlmrictte\n" + "nsmxhpothlulxivt\n" + "bvzbfcvenskqxejr\n" + "sdqeczmzpqqtqabq\n" + "bjveyzniaaliatkw\n"
      + "zxsqlntyjajjxytk\n" + "jkoxlerbtidsuepg\n" + "ewtlibdkeqwgxnqt\n" + "lmrshemwxrdwzrgc\n" + "nekcdyxmftlymfir\n"
      + "edaqvmulzkskzsfy\n" + "znmvqaupykjmyebx\n" + "ximtebuxwhqpzubd\n" + "rrlstppkknqyxlho\n" + "uyibwcitxixjfwcr\n"
      + "chrvoierkimesqmm\n" + "dltxmwhheldvxwqe\n" + "xfuthxjuuizanfjy\n" + "vtiwavmxwonpkpug\n" + "phchnujfnxewglht\n"
      + "owvmetdjcynohxtw\n" + "cbtujdrumixxatry\n" + "iirzildsfxipfipe\n" + "sqxcscqyofohotcy\n" + "sbubnekndkvovuqg\n"
      + "jzhsqqxqdrtibtcd\n" + "mscwasyvxkhlvwbn\n" + "bpafxtagbuxivbwz\n" + "uhvueesygaxrqffw\n" + "trrxlibhtmzuwkkl\n"
      + "yktkmkokmfslgkml\n" + "gfzzzdptaktytnqg\n" + "pgqmaiwzhplnbyhg\n" + "qjiptlkwfshunsfb\n" + "lewvlpescsyunxck\n"
      + "tywsfatykshogjas\n" + "qtrnwjjgxdektjgi\n" + "arypcritpwijczkn\n" + "jwxvngigbhfpiubf\n" + "upsjdctitlbqlnhf\n"
      + "lvpjlrpnmdjiscrq\n" + "jvzchdrsnkgpgsti\n" + "wuoesbwunpseyqzu\n" + "xuqspvoshgxmrnrb\n" + "icdawnmfnpnmyzof\n"
      + "hwcwtibgpvctznuo\n" + "bzdjrniddyamfloq\n" + "hffkxtzuazageruv\n" + "deixfxjvzbitalnc\n" + "zihsohukiqrgsnvw\n"
      + "nwoondfnlgowavkg\n" + "qnuulsywgnoillgn\n" + "koozejhfjyzuhviy\n" + "oetcoipohymhpump\n" + "cizwpfczfoodwuly\n"
      + "jghlinczhtaxifau\n" + "svjejifbidnvvdvy\n" + "rxmbsnaqhzcnbfcl\n" + "vveubmiecvdtrket\n" + "sbihpvrcnzjtgfep\n"
      + "iqbuljuxkwrlebvw\n" + "ptrhvxrpezqvmmvv\n" + "duwzugnhktpiybjw\n" + "lijafjnujfeflkva\n" + "coylvegferuuyfop\n"
      + "fowsjrgammrqkkof\n" + "pgmcruaioccmbrbz\n" + "osejwflxagwqtjoi\n" + "otqflckqgxzvtper\n" + "slwyntdcrncktoka\n"
      + "hzcdzsppcfkrblqg\n" + "jksdmmvtzkqaompg\n" + "galwwwgugetdohkg\n" + "zbghtjvuikmfjuef\n" + "dmqwcamjtlcofqib\n"
      + "zbczldlfdzemxeys\n" + "mdlqoklybhppdkwe\n" + "tuyajhkexrrrvnlb\n" + "ylfolaubymxmkowo\n" + "nnsyrfnoyrxswzxn\n"
      + "zkhunhhhigbsslfk\n" + "spbokzdfkbmflanz\n" + "zmzxvrwdhiegfely\n" + "imywhfczvmgahxwl\n" + "fnvabvxeiqvsarqq\n"
      + "yschramprctnputs\n" + "ubyjrgdzsvxzvouj\n" + "qnvdhpptympctfer\n" + "smipxcntyhjpowug\n" + "ouhjibgcmotegljy\n"
      + "zpflubaijjqqsptz\n" + "fgysnxrnfnxprdmf\n" + "pbpznrexzxomzfvj\n" + "thhzjresjpmnwtdv\n" + "sbmokolkhvbfqmua\n"
      + "sxxpdohxlezmqhhx\n" + "pevvsyqgoirixtqh\n" + "wdxrornmhqsbfznb\n" + "zjqziqbctxkshqcn\n" + "nbqcwpzfwfaahylk\n"
      + "bxbvkonpcxprxqjf\n" + "xplbpqcnwzwqxheb\n" + "prsakggmnjibrpoy\n" + "xoguxbpnrvyqarjl\n" + "ilrgryrmgwjvpzjy\n"
      + "efwrmokaoigjtrij\n" + "yhcncebopycjzuli\n" + "gwcmzbzaissohjgn\n" + "lggmemwbbjuijtcf\n" + "fkqedbfrluvkrwwl\n"
      + "jcbppekecevkwpuk\n" + "onvolrckkxeyzfjt\n" + "zzousprgrmllxboy\n" + "cajthmamvxuesujl\n" + "rmiozfsikufkntpg\n"
      + "lvekypkwjbpddkcv\n" + "dwaqzfnzcnabersa\n" + "pcdsskjopcqwhyis\n" + "uabepbrrnxfbpyvx\n" + "yxlgdomczciiunrk\n"
      + "ccerskfzctqxvrkz\n" + "edvmkntljlncwhax\n" + "xtcbwecdwygrvowo\n" + "axqgqjqkqwrgcqot\n" + "tyjrynolpzqwnjgj\n"
      + "thrtmlegdjsuofga\n" + "mpgoeqkzzqqugait\n" + "emuslxgoefdjyivl\n" + "klehpcehdznpssfb\n" + "xfgvugyrdxolixkc\n"
      + "acenyrbdwxywmwst\n" + "yqgperajsfsamgan\n" + "dbjxlnumrmhipquw\n" + "hsnhirmswcenewxm\n" + "qehqkbhmgucjjpwo\n"
      + "gprjdglsbtsfzqcw\n" + "wvqkyrkoratfmvfi\n" + "myhzlerupqbduqsl\n" + "couyazesiuhwwhht\n" + "scxzehubxhkfejrr\n"
      + "gqlitwfriqkmzqdd\n" + "pxtbmqelssoagxko\n" + "dzhklewjqzmrfzsw\n" + "yxgeypduywntnbji\n" + "kwzbgzhkzbgedlfh\n"
      + "vukmuyfstgmscuab\n" + "vcmaybfvdgwnasgt\n" + "qmybkqqdhjigzmum\n" + "cbnuicuncvczyalu\n" + "qdgpsdpdlgjasjqr\n"
      + "kdzxqqheurupejjo\n" + "mcatrxfchbqnxelm\n" + "badunwkeggdkcgco\n" + "ntaeanvcylpoqmxi\n" + "ghnyfytpzgvuokjn\n"
      + "ozepydixmjijdmts\n" + "qefcfwzdhwmcyfvp\n" + "ycyktmpaqgaxqsxt\n" + "edpizkxnsxeeebfl\n" + "uwciveajsxxwoqyr\n"
      + "rbvjkljpxtglqjsh\n" + "nbplrskduutrptfk\n" + "vewrbadvkseuloec\n" + "upaotnjxquomoflx\n" + "qfwxkinrousqywdd\n"
      + "mqzxvvskslbxvyjt\n" + "oxicszyiqifoyugx\n" + "bkitxwzjpabvhraj\n" + "ydrbyjecggynjpir\n" + "hezyteaublxxpamq\n"
      + "hxkuektnoovsehnd\n" + "cwtbbavnhlpiknza\n" + "qrwvkhbyasgfxwol\n" + "qryjbohkprfazczc\n" + "wjksnogpxracrbud\n"
      + "znmsxbhliqxhvesr\n" + "gkippedrjzmnnwkp\n" + "pklylwsnsyyxwcwg\n" + "osdpwbxoegwaiemr\n" + "kpslrrrljgtjiqka\n"
      + "vuqkloqucpyzfxgk\n" + "bvtdsisgvkuzghyl\n" + "qlcayluuyvlhdfyy\n" + "kbimqwnzanlygaya\n" + "nvoeanlcfhczijed\n"
      + "kqvcijcuobtdwvou\n" + "pmhdpcmxnprixitl\n" + "yueilssewzabzmij\n" + "zqxhafrvjyeyznyg\n" + "mhdounmxkvnnsekx\n"
      + "hnacyglnzicxjakg\n" + "iaxfdqibnrcjdlyl\n" + "iypoelspioegrwix\n" + "uiqouxzmlnjxnbqt\n" + "kslgjfmofraorvjo\n"
      + "bgvotsdqcdlpkynk\n" + "huwcgxhvrrbvmmth\n" + "vpqyfnkqqjacpffw\n" + "hpjgdfovgmrzvrcl\n" + "vbntbhbvdeszihzj\n"
      + "nrbyyuviwyildzuw\n" + "wckeoadqzsdnsbox\n" + "xgsobwuseofxsxox\n" + "anvhsxdshndembsd\n" + "iygmhbegrwqbqerg\n"
      + "ylrsnwtmdsrgsvlh\n" + "zvvejnrarsavahvc\n" + "yncxhmmdtxxeafby\n" + "kekgiglblctktnes\n" + "uoqgymsrlrwdruzc\n"
      + "saaoymtmnykusicw\n" + "bqvcworpqimwglcp\n" + "zbpgtheydoyzipjv\n" + "pkykzslwsjbhcvcj\n" + "jhwxxneyuuidrzvl\n"
      + "pafeyajcrlehmant\n" + "klszcvtmcdeyfsmj\n" + "ledsltggvrbvlefn\n" + "hubpbvxknepammep\n" + "gthxhaapfpgtilal\n"
      + "jtfhbozlometwztj\n" + "jrhshycyenurbpwb\n" + "fyaxbawrsievljqv\n" + "lgfcgbenlqxqcxsd\n" + "dhedabbwbdbpfmxp\n"
      + "mxzgwhaqobyvckcm\n" + "qboxojoykxvwexav\n" + "jcpzfjnmvguwjnum\n" + "ohpsxnspfwxkkuqe\n" + "nyekrqjlizztwjqp\n"
      + "thuynotacpxjzroj\n" + "wymbolrlwosnbxqx\n" + "iyaqihnqvewxdtjm\n" + "hdvdbtvfpdrejenu\n" + "gtjscincktlwwkkf\n"
      + "wtebigbaythklkbd";
}