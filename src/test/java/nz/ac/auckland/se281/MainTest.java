package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import static nz.ac.auckland.se281.Main.Command.*;

@RunWith(Suite.class)
@SuiteClasses({
  MainTest.Task1.class,
  MainTest.Task2.class,
  MainTest.Task3.class,
  MainTest.YourTests.class,
  MainTest.CustomTask2.class,
  MainTest.CustomTask3.class,
  MainTest.OthersTestsTask1.class,
  MainTest.OthersTestsTask2.class,
  MainTest.OthersTestsTask3.class
})
public class MainTest {

  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  public static class Task1 extends CliTest {

      public Task1() {
          super(Main.class);
      }

      @Test
      public void T1_01_zero_operators() throws Exception {
          runCommands(SEARCH_OPERATORS, "*", EXIT);

          assertContains("There are no matching operators found.");
          assertDoesNotContain("There is", true);
      }

      @Test
      public void T1_02_create_operator_name() throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'West Auckland Camel Treks'",
              "'AKL'",
              EXIT
          );

          assertContains(
              "Successfully created operator 'West Auckland Camel Treks'"
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
          assertDoesNotContain("There are", true);
      }

      @Test
      public void T1_03_create_operator_location() throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'West Auckland Camel Treks'",
              "'AKL'",
              EXIT
          );

          assertContains(
              "Successfully created operator 'West Auckland Camel Treks'"
          );
          assertContains("located in 'Auckland | Tāmaki Makaurau'.");
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
          assertDoesNotContain("There are", true);
      }

      @Test
      public void T1_04_create_operator_id_part_name() throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'West Auckland Camel Treks'",
              "'AKL'",
              EXIT
          );

          assertContains(
              "Successfully created operator 'West Auckland Camel Treks'"
          );
          assertContains("located in 'Auckland | Tāmaki Makaurau'.");
          assertContains("WACT");
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
          assertDoesNotContain("There are", true);
      }

      @Test
      public void T1_05_create_operator_id_full() throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'West Auckland Camel Treks'",
              "'AKL'",
              EXIT
          );

          assertContains(
              "Successfully created operator 'West Auckland Camel Treks' ('WACT-AKL-001') located in" +
              " 'Auckland | Tāmaki Makaurau'."
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
          assertDoesNotContain("There are", true);
      }

      @Test
      public void T1_06_create_operator_name_too_short() throws Exception {
          runCommands(CREATE_OPERATOR, "'Yo'", "'AKL'", EXIT);

          assertContains(
              "Operator not created: 'Yo' is not a valid operator name."
          );
          assertDoesNotContain("Successfully", true);
          assertDoesNotContain("There is", true);
          assertDoesNotContain("There are", true);
      }

      @Test
      public void T1_07_create_operator_invalid_location() throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'West Auckland Camel Treks'",
              "'XYZ'",
              EXIT
          );

          assertContains(
              "Operator not created: 'XYZ' is an invalid location."
          );
          assertDoesNotContain("Successfully", true);
          assertDoesNotContain("There is", true);
          assertDoesNotContain("There are", true);
      }

      // specify location in full name
      @Test
      public void T1_08_create_operator_valid_location_full_name_english()
          throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'Parliament Bungee Jump'",
              "'Wellington'",
              EXIT
          );

          assertContains(
              "Successfully created operator 'Parliament Bungee Jump' ('PBJ-WLG-001') located in" +
              " 'Wellington | Te Whanganui-a-Tara'."
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
          assertDoesNotContain("There are", true);
      }

      @Test
      public void T1_09_create_operator_valid_location_full_name_teo_reo()
          throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'Parliament Bungee Jump'",
              "'Te Whanganui-a-Tara'",
              EXIT
          );

          assertContains(
              "Successfully created operator 'Parliament Bungee Jump' ('PBJ-WLG-001') located in" +
              " 'Wellington | Te Whanganui-a-Tara'."
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
          assertDoesNotContain("There are", true);
      }

      // SHOW used in handout
      @Test
      public void T1_10_create_operator_saved() throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'West Auckland Camel Treks'",
              "'AKL'", //
              SEARCH_OPERATORS,
              "*", //
              EXIT
          );

          assertContains("There is 1 matching operator found:");
          assertContains(
              "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki" +
              " Makaurau')"
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There are", true);
      }

      // operator in Tauranga 'Shark Snorkel Bay'
      @Test
      public void T1_11_create_operator_saved_english_tereo_match()
          throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'Shark Snorkel Bay'",
              "'Tauranga'", //
              SEARCH_OPERATORS,
              "*", //
              EXIT
          );

          assertContains("There is 1 matching operator found:");
          assertContains(
              "* Shark Snorkel Bay ('SSB-TRG-001' located in 'Tauranga')"
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There are", true);
          assertDoesNotContain("'Tauranga | Tauranga'", true);
      }

      @Test
      public void T1_12_create_operator_saved_two() throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'West Auckland Camel Treks'",
              "'AKL'", //
              CREATE_OPERATOR,
              "'Parliament Bungee Jump'",
              "'WLG'", //
              SEARCH_OPERATORS,
              "*", //
              EXIT
          );

          assertContains("There are 2 matching operators found:");
          assertContains(
              "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki" +
              " Makaurau')"
          );
          assertContains(
              "* Parliament Bungee Jump ('PBJ-WLG-001' located in 'Wellington | Te Whanganui-a-Tara')"
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
      }

      @Test
      public void T1_13_create_operator_same_name_same_location()
          throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'West Auckland Camel Treks'",
              "'AKL'", //
              CREATE_OPERATOR,
              "'West Auckland Camel Treks'",
              "'AKL'", //
              SEARCH_OPERATORS,
              "*", //
              EXIT
          );

          assertContains(
              "Successfully created operator 'West Auckland Camel Treks' ('WACT-AKL-001') located in" +
              " 'Auckland | Tāmaki Makaurau'."
          );
          assertContains(
              "Operator not created: the operator name 'West Auckland Camel Treks' already exists same" +
              " location for 'Auckland | Tāmaki Makaurau'."
          );
          assertContains("There is 1 matching operator found:");
          assertContains(
              "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki" +
              " Makaurau')"
          );
          assertDoesNotContain("002", true);
          assertDoesNotContain("There are", true);
      }

      @Test
      public void T1_14_create_operator_same_location() throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'Parliament Bungee Jump'",
              "'Wellington'", //
              CREATE_OPERATOR,
              "'Parliament Bungee Jump'",
              "'Te Whanganui-a-Tara'", //
              SEARCH_OPERATORS,
              "*", //
              EXIT
          );

          assertContains(
              "Successfully created operator 'Parliament Bungee Jump' ('PBJ-WLG-001') located in" +
              " 'Wellington | Te Whanganui-a-Tara'."
          );
          assertContains(
              "Operator not created: the operator name 'Parliament Bungee Jump' already exists same" +
              " location for 'Wellington | Te Whanganui-a-Tara'."
          );
          assertContains("There is 1 matching operator found:");
          assertContains(
              "* Parliament Bungee Jump ('PBJ-WLG-001' located in 'Wellington | Te Whanganui-a-Tara')"
          );
          assertDoesNotContain("002", true);
          assertDoesNotContain("There are", true);
      }

      @Test
      public void T1_15_create_operator_same_name_different_location()
          throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'Volcano Bungee Jump'",
              "'WLG'", //
              CREATE_OPERATOR,
              "'Volcano Bungee Jump'",
              "'AKL'", //
              SEARCH_OPERATORS,
              "*", //
              EXIT
          );

          assertContains(
              "Successfully created operator 'Volcano Bungee Jump' ('VBJ-WLG-001') located in" +
              " 'Wellington | Te Whanganui-a-Tara'."
          );
          assertContains(
              "Successfully created operator 'Volcano Bungee Jump' ('VBJ-AKL-001') located in" +
              " 'Auckland | Tāmaki Makaurau'."
          );
          assertContains("There are 2 matching operators found:");
          assertContains(
              "* Volcano Bungee Jump ('VBJ-WLG-001' located in 'Wellington | Te Whanganui-a-Tara')"
          );
          assertContains(
              "* Volcano Bungee Jump ('VBJ-AKL-001' located in 'Auckland | Tāmaki" +
              " Makaurau')"
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
      }

      @Test
      public void T1_16_create_operator_same_name_different_full_location()
          throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'Volcano Bungee Jump'",
              "'Taupo'", //
              CREATE_OPERATOR,
              "'Volcano Bungee Jump'",
              "'Tauranga'", //
              SEARCH_OPERATORS,
              "*", //
              EXIT
          );

          assertContains(
              "Successfully created operator 'Volcano Bungee Jump' ('VBJ-TUO-001') located in" +
              " 'Taupo | Taupō-nui-a-Tia'."
          );
          assertContains(
              "Successfully created operator 'Volcano Bungee Jump' ('VBJ-TRG-001') located in" +
              " 'Tauranga'."
          );
          assertContains("There are 2 matching operators found:");
          assertContains(
              "* Volcano Bungee Jump ('VBJ-TUO-001' located in 'Taupo | Taupō-nui-a-Tia')"
          );
          assertContains(
              "* Volcano Bungee Jump ('VBJ-TRG-001' located in 'Tauranga')"
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
      }

      @Test
      public void T1_17_create_operator_three_locations() throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'Volcano Bungee Jump'",
              "'Taupo'", //
              CREATE_OPERATOR,
              "'Volcano Bungee Jump'",
              "'Tauranga'", //
              CREATE_OPERATOR,
              "'Volcano Bungee Jump'",
              "'Auckland'", //
              SEARCH_OPERATORS,
              "*", //
              EXIT
          );

          assertContains(
              "Successfully created operator 'Volcano Bungee Jump' ('VBJ-TUO-001') located in" +
              " 'Taupo | Taupō-nui-a-Tia'."
          );
          assertContains(
              "Successfully created operator 'Volcano Bungee Jump' ('VBJ-TRG-001') located in" +
              " 'Tauranga'."
          );
          assertContains(
              "Successfully created operator 'Volcano Bungee Jump' ('VBJ-AKL-001') located in" +
              " 'Auckland | Tāmaki Makaurau'."
          );
          assertContains("There are 3 matching operators found:");
          assertContains(
              "* Volcano Bungee Jump ('VBJ-TUO-001' located in 'Taupo | Taupō-nui-a-Tia')"
          );
          assertContains(
              "* Volcano Bungee Jump ('VBJ-TRG-001' located in 'Tauranga')"
          );
          assertContains(
              "* Volcano Bungee Jump ('VBJ-AKL-001' located in 'Auckland | Tāmaki Makaurau')"
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
      }

      @Test
      public void T1_18_create_operator_same_location_three()
          throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'Mystical Waikato Whale Watching'",
              "'HLZ'", //
              CREATE_OPERATOR,
              "'Glowworm Fireworks Adventures'",
              "'Kirikiriroa'", //
              CREATE_OPERATOR,
              "'Hobbiton Skydiving Tours'",
              "'Hamilton'", //
              SEARCH_OPERATORS,
              "*", //
              EXIT
          );

          assertContains(
              "Successfully created operator 'Mystical Waikato Whale Watching' ('MWWW-HLZ-001') located" +
              " in 'Hamilton | Kirikiriroa'."
          );
          assertContains(
              "Successfully created operator 'Glowworm Fireworks Adventures' ('GFA-HLZ-002') located in" +
              " 'Hamilton | Kirikiriroa'."
          );
          assertContains(
              "Successfully created operator 'Hobbiton Skydiving Tours' ('HST-HLZ-003') located in" +
              " 'Hamilton | Kirikiriroa'."
          );
          assertContains("There are 3 matching operators found:");
          assertContains(
              "* Mystical Waikato Whale Watching ('MWWW-HLZ-001' located in 'Hamilton | Kirikiriroa')"
          );
          assertContains(
              "* Glowworm Fireworks Adventures ('GFA-HLZ-002' located in 'Hamilton | Kirikiriroa')"
          );
          assertContains(
              "* Hobbiton Skydiving Tours ('HST-HLZ-003' located in 'Hamilton | Kirikiriroa')"
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
      }

      // 5 operators in same and mixed locations
      // Mystical Waikato Whale Watching – Says you can see whales in the Waikato River.
      // Glowworm Fireworks Adventures – Promises glowworms that shoot fireworks (they don’t).
      // Hobbiton Skydiving Tours – Parachute directly into Hobbiton (this does not exist).

      // Tauranga (Tauranga, TRG)
      // Mount Maunganui Ski Resort
      // Shark Snorkel Bay

      @Test
      public void T1_19_create_operators_mixed_and_same_locations()
          throws Exception {
          runCommands(
              CREATE_OPERATOR,
              "'Mystical Waikato Whale Watching'",
              "'HLZ'", //
              CREATE_OPERATOR,
              "'Glowworm Fireworks Adventures'",
              "'Kirikiriroa'", //
              CREATE_OPERATOR,
              "'Hobbiton Skydiving Tours'",
              "'Hamilton'", //
              CREATE_OPERATOR,
              "'Mount Maunganui Ski Resort'",
              "'Tauranga'", //
              CREATE_OPERATOR,
              "'Shark Snorkel Bay'",
              "'Tauranga'", //
              SEARCH_OPERATORS,
              "*", //
              EXIT
          );

          assertContains(
              "Successfully created operator 'Mystical Waikato Whale Watching' ('MWWW-HLZ-001') located" +
              " in 'Hamilton | Kirikiriroa'."
          );
          assertContains(
              "Successfully created operator 'Glowworm Fireworks Adventures' ('GFA-HLZ-002') located in" +
              " 'Hamilton | Kirikiriroa'."
          );
          assertContains(
              "Successfully created operator 'Hobbiton Skydiving Tours' ('HST-HLZ-003') located in" +
              " 'Hamilton | Kirikiriroa'."
          );
          assertContains(
              "Successfully created operator 'Mount Maunganui Ski Resort' ('MMSR-TRG-001') located in" +
              " 'Tauranga'."
          );
          assertContains(
              "Successfully created operator 'Shark Snorkel Bay' ('SSB-TRG-002') located in" +
              " 'Tauranga'."
          );
          assertContains("There are 5 matching operators found:");
          assertContains(
              "* Mystical Waikato Whale Watching ('MWWW-HLZ-001' located in 'Hamilton | Kirikiriroa')"
          );
          assertContains(
              "* Glowworm Fireworks Adventures ('GFA-HLZ-002' located in 'Hamilton | Kirikiriroa')"
          );
          assertContains(
              "* Hobbiton Skydiving Tours ('HST-HLZ-003' located in 'Hamilton | Kirikiriroa')"
          );
          assertContains(
              "* Mount Maunganui Ski Resort ('MMSR-TRG-001' located in 'Tauranga')"
          );
          assertContains(
              "* Shark Snorkel Bay ('SSB-TRG-002' located in 'Tauranga')"
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
      }

      @Test
      public void T1_20_create_14_operators() throws Exception {
          runCommands(unpack(CREATE_14_OPERATORS, EXIT));

          assertContains(
              "Successfully created operator 'West Auckland Camel Treks' ('WACT-AKL-001') located in" +
              " 'Auckland | Tāmaki Makaurau'."
          );
          assertContains(
              "Successfully created operator 'Volcano Bungee Jump' ('VBJ-AKL-002') located in" +
              " 'Auckland | Tāmaki Makaurau'."
          );
          assertContains(
              "Successfully created operator 'Mystical Waikato Whale Watching' ('MWWW-HLZ-001') located" +
              " in 'Hamilton | Kirikiriroa'."
          );
          assertContains(
              "Successfully created operator 'Hobbiton Skydiving Tours' ('HST-HLZ-002') located in" +
              " 'Hamilton | Kirikiriroa'."
          );
          assertContains(
              "Successfully created operator 'Mount Maunganui Ski Resort' ('MMSR-TRG-001') located in" +
              " 'Tauranga'."
          );
          assertContains(
              "Successfully created operator 'Shark Snorkel Bay' ('SSB-TRG-002') located in" +
              " 'Tauranga'."
          );
          assertContains(
              "Successfully created operator 'Huka Falls Barrel Rides' ('HFBR-TUO-001') located in" +
              " 'Taupo | Taupō-nui-a-Tia'."
          );
          assertContains(
              "Successfully created operator 'Taupo UFO Watching' ('TUW-TUO-002') located in" +
              " 'Taupo | Taupō-nui-a-Tia'."
          );
          assertContains(
              "Successfully created operator 'Parliament Bungee Jump' ('PBJ-WLG-001') located in" +
              " 'Wellington | Te Whanganui-a-Tara'."
          );
          assertContains(
              "Successfully created operator 'Nelson UFO Watching' ('NUW-NSN-001') located in" +
              " 'Nelson | Whakatu'."
          );
          assertContains(
              "Successfully created operator 'Christchurch Camel Treks' ('CCT-CHC-001') located in" +
              " 'Christchurch | Ōtautahi'."
          );
          assertContains(
              "Successfully created operator 'Avon River Whitewater Rafting' ('ARWR-CHC-002') located" +
              " in 'Christchurch | Ōtautahi'."
          );
          assertContains(
              "Successfully created operator 'Dunedin Penguin Parade' ('DPP-DUD-001') located in" +
              " 'Dunedin | Ōtepoti'."
          );
          assertContains(
              "Successfully created operator 'Baldwin Street Ski Jumping' ('BSSJ-DUD-002') located in" +
              " 'Dunedin | Ōtepoti'."
          );
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
          assertDoesNotContain("There are", true);
      }

      // HIDE
      @Test
      public void T1_21_create_14_operators_saved() throws Exception {
          runCommands(
              unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "*", EXIT)
          );

          assertContains(
              "Successfully created operator 'West Auckland Camel Treks' ('WACT-AKL-001') located in" +
              " 'Auckland | Tāmaki Makaurau'."
          );
          assertContains(
              "Successfully created operator 'Volcano Bungee Jump' ('VBJ-AKL-002') located in" +
              " 'Auckland | Tāmaki Makaurau'."
          );
          assertContains(
              "Successfully created operator 'Mystical Waikato Whale Watching' ('MWWW-HLZ-001') located" +
              " in 'Hamilton | Kirikiriroa'."
          );
          assertContains(
              "Successfully created operator 'Hobbiton Skydiving Tours' ('HST-HLZ-002') located in" +
              " 'Hamilton | Kirikiriroa'."
          );
          assertContains(
              "Successfully created operator 'Mount Maunganui Ski Resort' ('MMSR-TRG-001') located in" +
              " 'Tauranga'."
          );
          assertContains(
              "Successfully created operator 'Shark Snorkel Bay' ('SSB-TRG-002') located in" +
              " 'Tauranga'."
          );
          assertContains(
              "Successfully created operator 'Huka Falls Barrel Rides' ('HFBR-TUO-001') located in" +
              " 'Taupo | Taupō-nui-a-Tia'."
          );
          assertContains(
              "Successfully created operator 'Taupo UFO Watching' ('TUW-TUO-002') located in" +
              " 'Taupo | Taupō-nui-a-Tia'."
          );
          assertContains(
              "Successfully created operator 'Parliament Bungee Jump' ('PBJ-WLG-001') located in" +
              " 'Wellington | Te Whanganui-a-Tara'."
          );
          assertContains(
              "Successfully created operator 'Nelson UFO Watching' ('NUW-NSN-001') located in" +
              " 'Nelson | Whakatu'."
          );
          assertContains(
              "Successfully created operator 'Christchurch Camel Treks' ('CCT-CHC-001') located in" +
              " 'Christchurch | Ōtautahi'."
          );
          assertContains(
              "Successfully created operator 'Avon River Whitewater Rafting' ('ARWR-CHC-002') located" +
              " in 'Christchurch | Ōtautahi'."
          );
          assertContains(
              "Successfully created operator 'Dunedin Penguin Parade' ('DPP-DUD-001') located in" +
              " 'Dunedin | Ōtepoti'."
          );
          assertContains(
              "Successfully created operator 'Baldwin Street Ski Jumping' ('BSSJ-DUD-002') located in" +
              " 'Dunedin | Ōtepoti'."
          );
          assertContains("There are 14 matching operators found:");
          assertDoesNotContain("Operator not created", true);
          assertDoesNotContain("There is", true);
      }

      @Test
      public void T1_22_search_operators_specific_location_te_reo()
          throws Exception {
          runCommands(
              unpack(
                  CREATE_14_OPERATORS,
                  SEARCH_OPERATORS,
                  "'Tāmaki   '",
                  EXIT
              )
          );

          assertContains("There are 2 matching operators found:");
          assertContains(
              "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')"
          );
          assertContains(
              "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')"
          );
          assertDoesNotContain("There is", true);
          assertDoesNotContain("There are 14", true);
      }

      @Test
      public void T1_23_search_operators_specific_location_english()
          throws Exception {
          runCommands(
              unpack(
                  CREATE_14_OPERATORS,
                  SEARCH_OPERATORS,
                  "'Auckland'",
                  EXIT
              )
          );

          assertContains("There are 2 matching operators found:");
          assertContains(
              "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')"
          );
          assertContains(
              "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')"
          );
          assertDoesNotContain("There is", true);
      }

      @Test
      public void T1_24_search_operators_specific_location_abbreviation()
          throws Exception {
          runCommands(
              unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "akl", EXIT)
          );

          assertContains("There are 2 matching operators found:");
          assertContains(
              "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')"
          );
          assertContains(
              "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')"
          );
          assertDoesNotContain("There is", true);
      }

      @Test
      public void T1_25_search_operators_keyword_in_location()
          throws Exception {
          runCommands(
              unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "ranga", EXIT)
          );

          assertContains("There are 2 matching operators found:");
          assertContains(
              "* Mount Maunganui Ski Resort ('MMSR-TRG-001' located in 'Tauranga')"
          );
          assertContains(
              "* Shark Snorkel Bay ('SSB-TRG-002' located in 'Tauranga')"
          );
          assertDoesNotContain("There is", true);
          assertDoesNotContain("There are 14", true);
      }

      // HIDE
      @Test
      public void T1_26_search_operators_keyword_across_multiple_locations()
          throws Exception {
          runCommands(
              unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "tau", EXIT)
          );

          assertContains("There are 6 matching operators found:");
          assertContains(
              "* Mount Maunganui Ski Resort ('MMSR-TRG-001' located in 'Tauranga')"
          );
          assertContains(
              "* Shark Snorkel Bay ('SSB-TRG-002' located in 'Tauranga')"
          );
          assertContains(
              "* Huka Falls Barrel Rides ('HFBR-TUO-001' located in 'Taupo | Taupō-nui-a-Tia')"
          );
          assertContains(
              "* Taupo UFO Watching ('TUW-TUO-002' located in 'Taupo | Taupō-nui-a-Tia')"
          );
          assertContains(
              "* Christchurch Camel Treks ('CCT-CHC-001' located in 'Christchurch | Ōtautahi')"
          );
          assertContains(
              "* Avon River Whitewater Rafting ('ARWR-CHC-002' located in 'Christchurch | Ōtautahi')"
          );
          assertDoesNotContain("There is", true);
          assertDoesNotContain("There are 14", true);
      }

      // HIDE
      @Test
      public void T1_27_search_operators_keyword_no_match() throws Exception {
          runCommands(
              unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "xyz", EXIT)
          );

          assertContains("There are no matching operators found.");
          assertDoesNotContain("There is", true);
          assertDoesNotContain("matching operators found:", true);
      }

      @Test
      public void T1_28_search_operators_keyword_in_name() throws Exception {
          runCommands(
              unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "Avon", EXIT)
          );

          assertContains("There is 1 matching operator found:");
          assertContains(
              "* Avon River Whitewater Rafting ('ARWR-CHC-002' located in 'Christchurch | Ōtautahi')"
          );
          assertDoesNotContain("There are 14", true);
          assertDoesNotContain("no matching operators found", true);
      }

      // HIDE
      @Test
      public void T1_29_search_operators_keyword_in_name_multiple()
          throws Exception {
          runCommands(
              unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "bungee", EXIT)
          );

          assertContains("There are 2 matching operators found:");
          assertContains(
              "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')"
          );
          assertContains(
              "* Parliament Bungee Jump ('PBJ-WLG-001' located in 'Wellington | Te Whanganui-a-Tara')"
          );
          assertDoesNotContain("There are 14", true);
          assertDoesNotContain("no matching operators found", true);
      }

      @Test
      public void T1_30_search_operators_keyword_in_name_no_match()
          throws Exception {
          runCommands(
              unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "climbing", EXIT)
          );

          assertContains("There are no matching operators found.");
          assertDoesNotContain("There is", true);
          assertDoesNotContain("matching operators found:", true);
      }

      public static class YourTests extends CliTest {

          public YourTests() {
              super(Main.class);
          }
      }
  }

  // @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  // public static class Task1 extends CliTest {

  //   public Task1() {
  //     super(Main.class);
  //   }

  //   @Test
  //   public void T1_01_zero_operators() throws Exception {
  //     runCommands(SEARCH_OPERATORS, "*", EXIT);

  //     assertContains("There are no matching operators found.");
  //     assertDoesNotContain("There is", true);
  //   }

  //   @Test
  //   public void T1_02_create_operator_name() throws Exception {
  //     runCommands(CREATE_OPERATOR, "'West Auckland Camel Treks'", "'AKL'", EXIT);

  //     assertContains("Successfully created operator 'West Auckland Camel Treks'");
  //     assertDoesNotContain("Operator not created", true);
  //     assertDoesNotContain("There is", true);
  //     assertDoesNotContain("There are", true);
  //   }

  //   @Test
  //   public void T1_03_create_operator_location() throws Exception {
  //     runCommands(CREATE_OPERATOR, "'West Auckland Camel Treks'", "'AKL'", EXIT);

  //     assertContains("Successfully created operator 'West Auckland Camel Treks'");
  //     assertContains("located in 'Auckland | Tāmaki Makaurau'.");
  //     assertDoesNotContain("Operator not created", true);
  //     assertDoesNotContain("There is", true);
  //     assertDoesNotContain("There are", true);
  //   }

  //   @Test
  //   public void T1_04_create_operator_id_part_name() throws Exception {
  //     runCommands(CREATE_OPERATOR, "'West Auckland Camel Treks'", "'AKL'", EXIT);

  //     assertContains("Successfully created operator 'West Auckland Camel Treks'");
  //     assertContains("located in 'Auckland | Tāmaki Makaurau'.");
  //     assertContains("WACT");
  //     assertDoesNotContain("Operator not created", true);
  //     assertDoesNotContain("There is", true);
  //     assertDoesNotContain("There are", true);
  //   }

  //   @Test
  //   public void T1_05_create_operator_id_full() throws Exception {
  //     runCommands(CREATE_OPERATOR, "'West Auckland Camel Treks'", "'AKL'", EXIT);

  //     assertContains(
  //         "Successfully created operator 'West Auckland Camel Treks' ('WACT-AKL-001') located in"
  //             + " 'Auckland | Tāmaki Makaurau'.");
  //     assertDoesNotContain("Operator not created", true);
  //     assertDoesNotContain("There is", true);
  //     assertDoesNotContain("There are", true);
  //   }

  //   @Test
  //   public void T1_06_create_operator_valid_location_full_name_english() throws Exception {
  //     runCommands(CREATE_OPERATOR, "'Parliament Bungee Jump'", "'Wellington'", EXIT);

  //     assertContains(
  //         "Successfully created operator 'Parliament Bungee Jump' ('PBJ-WLG-001') located in"
  //             + " 'Wellington | Te Whanganui-a-Tara'.");
  //     assertDoesNotContain("Operator not created", true);
  //     assertDoesNotContain("There is", true);
  //     assertDoesNotContain("There are", true);
  //   }

  //   @Test
  //   public void T1_07_create_operator_valid_location_full_name_teo_reo() throws Exception {
  //     runCommands(CREATE_OPERATOR, "'Parliament Bungee Jump'", "'Te Whanganui-a-Tara'", EXIT);

  //     assertContains(
  //         "Successfully created operator 'Parliament Bungee Jump' ('PBJ-WLG-001') located in"
  //             + " 'Wellington | Te Whanganui-a-Tara'.");
  //     assertDoesNotContain("Operator not created", true);
  //     assertDoesNotContain("There is", true);
  //     assertDoesNotContain("There are", true);
  //   }

  //   @Test
  //   public void T1_08_create_operator_saved() throws Exception {
  //     runCommands(
  //         CREATE_OPERATOR,
  //         "'West Auckland Camel Treks'",
  //         "'AKL'", //
  //         SEARCH_OPERATORS,
  //         "*", //
  //         EXIT);

  //     assertContains("There is 1 matching operator found:");
  //     assertContains(
  //         "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki"
  //             + " Makaurau')");
  //     assertDoesNotContain("Operator not created", true);
  //     assertDoesNotContain("There are", true);
  //   }

  //   @Test
  //   public void T1_09_create_operator_saved_english_tereo_match() throws Exception {
  //     runCommands(
  //         CREATE_OPERATOR,
  //         "'Shark Snorkel Bay'",
  //         "'Tauranga'", //
  //         SEARCH_OPERATORS,
  //         "*", //
  //         EXIT);

  //     assertContains("There is 1 matching operator found:");
  //     assertContains("* Shark Snorkel Bay ('SSB-TRG-001' located in 'Tauranga')");
  //     assertDoesNotContain("Operator not created", true);
  //     assertDoesNotContain("There are", true);
  //     assertDoesNotContain("'Tauranga | Tauranga'", true);
  //   }

  //   @Test
  //   public void T1_10_create_operator_same_name_same_location() throws Exception {
  //     runCommands(
  //         CREATE_OPERATOR,
  //         "'West Auckland Camel Treks'",
  //         "'AKL'", //
  //         CREATE_OPERATOR,
  //         "'West Auckland Camel Treks'",
  //         "'AKL'", //
  //         SEARCH_OPERATORS,
  //         "*", //
  //         EXIT);

  //     assertContains(
  //         "Successfully created operator 'West Auckland Camel Treks' ('WACT-AKL-001') located in"
  //             + " 'Auckland | Tāmaki Makaurau'.");
  //     assertContains(
  //         "Operator not created: the operator name 'West Auckland Camel Treks' already exists same"
  //             + " location for 'Auckland | Tāmaki Makaurau'.");
  //     assertContains("There is 1 matching operator found:");
  //     assertContains(
  //         "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki"
  //             + " Makaurau')");
  //     assertDoesNotContain("002", true);
  //     assertDoesNotContain("There are", true);
  //   }

  //   @Test
  //   public void T1_11_create_operator_three_locations() throws Exception {
  //     runCommands(
  //         CREATE_OPERATOR,
  //         "'Volcano Bungee Jump'",
  //         "'Taupo'", //
  //         CREATE_OPERATOR,
  //         "'Volcano Bungee Jump'",
  //         "'Tauranga'", //
  //         CREATE_OPERATOR,
  //         "'Volcano Bungee Jump'",
  //         "'Auckland'", //
  //         SEARCH_OPERATORS,
  //         "*", //
  //         EXIT);

  //     assertContains(
  //         "Successfully created operator 'Volcano Bungee Jump' ('VBJ-TUO-001') located in"
  //             + " 'Taupo | Taupō-nui-a-Tia'.");
  //     assertContains(
  //         "Successfully created operator 'Volcano Bungee Jump' ('VBJ-TRG-001') located in"
  //             + " 'Tauranga'.");
  //     assertContains(
  //         "Successfully created operator 'Volcano Bungee Jump' ('VBJ-AKL-001') located in"
  //             + " 'Auckland | Tāmaki Makaurau'.");
  //     assertContains("There are 3 matching operators found:");
  //     assertContains("* Volcano Bungee Jump ('VBJ-TUO-001' located in 'Taupo | Taupō-nui-a-Tia')");
  //     assertContains("* Volcano Bungee Jump ('VBJ-TRG-001' located in 'Tauranga')");
  //     assertContains(
  //         "* Volcano Bungee Jump ('VBJ-AKL-001' located in 'Auckland | Tāmaki Makaurau')");
  //     assertDoesNotContain("Operator not created", true);
  //     assertDoesNotContain("There is", true);
  //   }

  //   @Test
  //   public void T1_12_create_14_operators() throws Exception {
  //     runCommands(unpack(CREATE_14_OPERATORS, EXIT));

  //     assertContains(
  //         "Successfully created operator 'West Auckland Camel Treks' ('WACT-AKL-001') located in"
  //             + " 'Auckland | Tāmaki Makaurau'.");
  //     assertContains(
  //         "Successfully created operator 'Volcano Bungee Jump' ('VBJ-AKL-002') located in"
  //             + " 'Auckland | Tāmaki Makaurau'.");
  //     assertContains(
  //         "Successfully created operator 'Mystical Waikato Whale Watching' ('MWWW-HLZ-001') located"
  //             + " in 'Hamilton | Kirikiriroa'.");
  //     assertContains(
  //         "Successfully created operator 'Hobbiton Skydiving Tours' ('HST-HLZ-002') located in"
  //             + " 'Hamilton | Kirikiriroa'.");
  //     assertContains(
  //         "Successfully created operator 'Mount Maunganui Ski Resort' ('MMSR-TRG-001') located in"
  //             + " 'Tauranga'.");
  //     assertContains(
  //         "Successfully created operator 'Shark Snorkel Bay' ('SSB-TRG-002') located in"
  //             + " 'Tauranga'.");
  //     assertContains(
  //         "Successfully created operator 'Huka Falls Barrel Rides' ('HFBR-TUO-001') located in"
  //             + " 'Taupo | Taupō-nui-a-Tia'.");
  //     assertContains(
  //         "Successfully created operator 'Taupo UFO Watching' ('TUW-TUO-002') located in"
  //             + " 'Taupo | Taupō-nui-a-Tia'.");
  //     assertContains(
  //         "Successfully created operator 'Parliament Bungee Jump' ('PBJ-WLG-001') located in"
  //             + " 'Wellington | Te Whanganui-a-Tara'.");
  //     assertContains(
  //         "Successfully created operator 'Nelson UFO Watching' ('NUW-NSN-001') located in"
  //             + " 'Nelson | Whakatu'.");
  //     assertContains(
  //         "Successfully created operator 'Christchurch Camel Treks' ('CCT-CHC-001') located in"
  //             + " 'Christchurch | Ōtautahi'.");
  //     assertContains(
  //         "Successfully created operator 'Avon River Whitewater Rafting' ('ARWR-CHC-002') located"
  //             + " in 'Christchurch | Ōtautahi'.");
  //     assertContains(
  //         "Successfully created operator 'Dunedin Penguin Parade' ('DPP-DUD-001') located in"
  //             + " 'Dunedin | Ōtepoti'.");
  //     assertContains(
  //         "Successfully created operator 'Baldwin Street Ski Jumping' ('BSSJ-DUD-002') located in"
  //             + " 'Dunedin | Ōtepoti'.");
  //     assertDoesNotContain("Operator not created", true);
  //     assertDoesNotContain("There is", true);
  //     assertDoesNotContain("There are", true);
  //   }

  //   @Test
  //   public void T1_13_search_operators_specific_location_te_reo() throws Exception {
  //     runCommands(unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "'Tāmaki Makaurau'", EXIT));

  //     assertContains("There are 2 matching operators found:");
  //     assertContains(
  //         "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')");
  //     assertContains(
  //         "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')");
  //     assertDoesNotContain("There is", true);
  //     assertDoesNotContain("There are 14", true);
  //   }

  //   @Test
  //   public void T1_14_search_operators_specific_location_english() throws Exception {
  //     runCommands(unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "'Auckland'", EXIT));

  //     assertContains("There are 2 matching operators found:");
  //     assertContains(
  //         "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')");
  //     assertContains(
  //         "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')");
  //     assertDoesNotContain("There is", true);
  //   }

  //   @Test
  //   public void T1_15_search_operators_keyword_in_location() throws Exception {
  //     runCommands(unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "ranga", EXIT));

  //     assertContains("There are 2 matching operators found:");
  //     assertContains("* Mount Maunganui Ski Resort ('MMSR-TRG-001' located in 'Tauranga')");
  //     assertContains("* Shark Snorkel Bay ('SSB-TRG-002' located in 'Tauranga')");
  //     assertDoesNotContain("There is", true);
  //     assertDoesNotContain("There are 14", true);
  //   }

  //   public static class YourTests extends CliTest {

  //     public YourTests() {
  //       super(Main.class);
  //     }
  //   }
  // }

  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  public static class Task2 extends CliTest {

    public Task2() {
      super(Main.class);
    }

    @Override
    public void reset() {}

    @Test
    public void T2_01_view_activities_invalid_operator() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, VIEW_ACTIVITIES, "UHOH-AKL-789", EXIT));

      assertContains("Operator not found: 'UHOH-AKL-789' is an invalid operator ID.");
      assertDoesNotContain("There is", true);
      assertDoesNotContain("There are", true);
    }

    @Test
    public void T2_02_view_activities_no_activities() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, VIEW_ACTIVITIES, "WACT-AKL-001", EXIT));

      assertContains("There are no matching activities found.");
      assertDoesNotContain("There is", true);
    }

    @Test
    public void T2_03_create_activity_name_too_short() throws Exception {
      runCommands(
          unpack(CREATE_14_OPERATORS, CREATE_ACTIVITY, "Hi", "ADVENTURE", "'WACT-AKL-001'", EXIT));

      assertContains("Activity not created: 'Hi' is not a valid activity name.");
      assertDoesNotContain("Successfully created activity", true);
    }

    @Test
    public void T2_04_create_activity_invalid_operator() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_ACTIVITY,
              "'Bethells Beach Camel Trek'",
              "ADVENTURE",
              "'WACT-AKL-789'",
              EXIT));

      assertContains("Activity not created: 'WACT-AKL-789' is an invalid operator ID.");
      assertDoesNotContain("Successfully created activity", true);
    }

    @Test
    public void T2_05_create_activity_success_activity_id() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_ACTIVITY,
              "'Bethells Beach Camel Trek'",
              "Adventure",
              "'WACT-AKL-001'",
              EXIT));

      assertContains("Successfully created activity");
      assertContains("WACT-AKL-001-001");
      assertDoesNotContain("Activity not created", true);
    }

    @Test
    public void T2_06_create_activity_success_full_details() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_ACTIVITY,
              "'Bethells Beach Camel Trek'",
              "Adventure",
              "'WACT-AKL-001'",
              EXIT));

      assertContains(
          "Successfully created activity 'Bethells Beach Camel Trek' ('WACT-AKL-001-001':"
              + " 'Adventure') for 'West Auckland Camel Treks'.");
      assertDoesNotContain("Activity not created", true);
    }

    @Test
    public void T2_07_saved_activity_details_single_operator() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_ACTIVITY,
              "'Bethells Beach Camel Trek'",
              "Adventure",
              "'WACT-AKL-001'",
              CREATE_ACTIVITY,
              "'Sky Tower Base Jumping'",
              "Adventure",
              "'WACT-AKL-001'",
              VIEW_ACTIVITIES,
              "'WACT-AKL-001'",
              EXIT));

      assertContains("There are 2 matching activities found:");
      assertContains(
          "* Bethells Beach Camel Trek: [WACT-AKL-001-001/Adventure] offered by West Auckland Camel"
              + " Treks");
      assertContains(
          "* Sky Tower Base Jumping: [WACT-AKL-001-002/Adventure] offered by West Auckland Camel"
              + " Treks");
      assertDoesNotContain("There is", true);
    }

    @Test
    public void T2_08_create_27_activities() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, CREATE_27_ACTIVITIES, EXIT));

      assertContains("Successfully created activity");
      assertDoesNotContain("Activity not created", true);
    }

    @Test
    public void T2_09_search_activities_all_no_activities_exist() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, SEARCH_ACTIVITIES, "*", EXIT));

      assertContains("There are no matching activities found.");
      assertDoesNotContain("There is", true);
      assertDoesNotContain("There are 27", true);
    }

    @Test
    public void T2_10_search_activities_none_found() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, SEARCH_ACTIVITIES, "Swimming", EXIT));

      assertContains("There are no matching activities found.");
      assertDoesNotContain("There is", true);
      assertDoesNotContain("There are 27", true);
    }

    @Test
    public void T2_11_search_activities_found_all() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, CREATE_27_ACTIVITIES, SEARCH_ACTIVITIES, "*", EXIT));

      assertContains("There are 27 matching activities found:");
      assertDoesNotContain("There are 28", true);
    }

    @Test
    public void T2_12_search_activities_found_keyword_activity_name() throws Exception {
      runCommands(
          unpack(CREATE_14_OPERATORS, CREATE_27_ACTIVITIES, SEARCH_ACTIVITIES, "Spaceships", EXIT));

      assertContains("There is 1 matching activity found:");
      assertContains(
          "* Stars or Spaceships?: [NUW-NSN-001-001/Scenic] offered by Nelson UFO Watching");
      assertDoesNotContain("zero", true);
      assertDoesNotContain("There are", true);
    }

    @Test
    public void T2_13_search_activities_found_keyword_activity_type() throws Exception {
      runCommands(
          unpack(CREATE_14_OPERATORS, CREATE_27_ACTIVITIES, SEARCH_ACTIVITIES, "Culture", EXIT));

      assertContains("There are 2 matching activities found:");
      assertContains(
          "* Jumping Through Political Loopholes: [PBJ-WLG-001-001/Culture] offered by Parliament"
              + " Bungee Jump");
      assertContains(
          "* Legends of the Lost Snow: [MMSR-TRG-001-001/Culture] offered by Mount Maunganui Ski"
              + " Resort");
      assertDoesNotContain("zero", true);
      assertDoesNotContain("There is 1", true);
    }

    @Test
    public void T2_14_search_activities_found_keyword_location_te_reo() throws Exception {
      runCommands(
          unpack(CREATE_14_OPERATORS, CREATE_27_ACTIVITIES, SEARCH_ACTIVITIES, "Whakatu", EXIT));

      assertContains("There are 2 matching activities found:");
      assertContains(
          "* Stars or Spaceships?: [NUW-NSN-001-001/Scenic] offered by Nelson UFO Watching");
      assertContains(
          "* Meteorites & Meat Pies: [NUW-NSN-001-002/Food] offered by Nelson UFO Watching");
      assertDoesNotContain("zero", true);
      assertDoesNotContain("There is 1", true);
    }

    @Test
    public void T2_15_search_activities_found_keyword_partial_various() throws Exception {
      runCommands(
          unpack(CREATE_14_OPERATORS, CREATE_27_ACTIVITIES, SEARCH_ACTIVITIES, "the", EXIT));

      assertContains("There are 5 matching activities found:");
      assertContains(
          "* The Frodo Jump: [HST-HLZ-002-001/Adventure] offered by Hobbiton Skydiving Tours");
      assertContains(
          "* The Gandalf Picnic: [HST-HLZ-002-002/Food] offered by Hobbiton Skydiving Tours");
      assertContains(
          "* The Frodo Jump: [HST-HLZ-002-001/Adventure] offered by Hobbiton Skydiving Tours");
      assertContains(
          "* The Gandalf Picnic: [HST-HLZ-002-002/Food] offered by Hobbiton Skydiving Tours");
      assertContains(
          "* Close Encounters of the Lake: [TUW-TUO-002-002/Wildlife] offered by Taupo UFO"
              + " Watching");

      assertDoesNotContain("zero", true);
      assertDoesNotContain("There is 1", true);
      assertDoesNotContain("There are 4", true);
    }
  }

  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  public static class Task3 extends CliTest {

    public Task3() {
      super(Main.class);
    }

    @Override
    public void reset() {}

    @Test
    public void T3_01_display_reviews_no_reviews() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              DISPLAY_REVIEWS,
              "WACT-AKL-001-001",
              DISPLAY_REVIEWS,
              "TUW-TUO-002-002",
              DISPLAY_REVIEWS,
              "ARWR-CHC-002-003",
              EXIT));

      assertContains("There are no reviews for activity 'Bethells Beach Camel Trek'.");
      assertContains("There are no reviews for activity 'Close Encounters of the Lake'.");
      assertContains("There are no reviews for activity 'River Rush'.");
      assertDoesNotContain("There is 1", true);
    }

    @Test
    public void T3_02_add_public_review_invalid_activity() throws Exception {
      runCommands(ADD_PUBLIC_REVIEW, "WACT-AKL-001-999", options("Alice", "n", "5", "Nice"), EXIT);

      assertContains("Review not added: 'WACT-AKL-001-999' is an invalid activity ID.");
      assertDoesNotContain("added successfully for activity", true);
    }

    @Test
    public void T3_03_add_public_review_everything_ok() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PUBLIC_REVIEW,
              "WACT-AKL-001-001",
              options("Alice", "n", "5", "Nice"),
              EXIT));

      assertContains("added successfully for activity ");
      assertDoesNotContain("Review not added", true);
    }

    @Test
    public void T3_04_add_other_reviews_everything_ok() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PRIVATE_REVIEW,
              "WACT-AKL-001-001",
              options("Felicia", "felicia@email.com", "5", "Great", "n"),
              ADD_EXPERT_REVIEW,
              "WACT-AKL-001-001",
              options("Eve", "4", "Good", "y"),
              EXIT));

      assertContains("Private review '");
      assertContains("Expert review '");
      assertContains("added successfully for activity");
      assertDoesNotContain("Review not added", true);
    }

    @Test
    public void T3_05_add_other_reviews_everything_ok_full_details() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PRIVATE_REVIEW,
              "WACT-AKL-001-001",
              options("Felicia", "felicia@email.com", "5", "Great", "n"),
              ADD_EXPERT_REVIEW,
              "WACT-AKL-001-001",
              options("Eve", "4", "Good", "y"),
              EXIT));

      assertContains(
          "Private review 'WACT-AKL-001-001-R1' added successfully for activity"
              + " 'Bethells Beach Camel Trek'.");
      assertContains(
          "Expert review 'WACT-AKL-001-001-R2' added successfully for activity"
              + " 'Bethells Beach Camel Trek'.");
      assertDoesNotContain("Review not added", true);
    }

    @Test
    public void T3_06_add_reviews_different_activities_correct_ids() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PUBLIC_REVIEW,
              "SSB-TRG-002-001",
              options("Alice", "n", "3", "Could be better"),
              ADD_PRIVATE_REVIEW,
              "TUW-TUO-002-002",
              options(
                  "Felicia", "felicia@email.com", "5", "Thank you for the great experience!", "n"),
              ADD_EXPERT_REVIEW,
              "ARWR-CHC-002-003",
              options("Eve", "4", "Lots of rapids, very scary, watch out!", "y"),
              EXIT));

      assertContains("'SSB-TRG-002-001-R1'");
      assertContains("'TUW-TUO-002-002-R1'");
      assertContains("'ARWR-CHC-002-003-R1'");
      assertDoesNotContain("Review not added", true);
    }

    @Test
    public void T3_07_add_reviews_different_activities_correct_activities() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PUBLIC_REVIEW,
              "SSB-TRG-002-001",
              options("Alice", "n", "3", "Could be better"),
              ADD_PRIVATE_REVIEW,
              "TUW-TUO-002-002",
              options(
                  "Felicia", "felicia@email.com", "5", "Thank you for the great experience!", "n"),
              ADD_EXPERT_REVIEW,
              "ARWR-CHC-002-003",
              options("Eve", "4", "Lots of rapids, very scary, watch out!", "y"),
              EXIT));

      assertContains("'Nemos Playground'.");
      assertContains("'Close Encounters of the Lake'.");
      assertContains("'River Rush'.");
      assertDoesNotContain("Review not added", true);
    }

    @Test
    public void T3_08_public_review_saved() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PUBLIC_REVIEW,
              "SSB-TRG-002-001",
              options("Alice", "n", "3", "Could be better"),
              DISPLAY_REVIEWS,
              "SSB-TRG-002-001",
              EXIT));

      assertContains("There is 1 review for activity 'Nemos Playground'.");
      assertContains("* [3/5] Public review (SSB-TRG-002-001-R1) by 'Alice'");
      assertContains("\"Could be better\"");
      assertDoesNotContain("There are", true);
    }

    @Test
    public void T3_09_private_review_saved() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PRIVATE_REVIEW,
              "TUW-TUO-002-002",
              options(
                  "Felicia", "felicia@email.com", "5", "Thank you for the great experience!", "n"),
              DISPLAY_REVIEWS,
              "TUW-TUO-002-002",
              EXIT));

      assertContains("There is 1 review for activity 'Close Encounters of the Lake'.");
      assertContains("* [5/5] Private review (TUW-TUO-002-002-R1) by 'Felicia'");
      assertContains("Resolved: \"-\"");
      assertContains("\"Thank you for the great experience!\"");
      assertDoesNotContain("There are", true);
      assertDoesNotContain("Need to email", true);
    }

    @Test
    public void T3_10_display_reviews_multiple_reviews() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PUBLIC_REVIEW,
              "SSB-TRG-002-001",
              options("Alice", "n", "3", "Could be better"),
              ADD_PRIVATE_REVIEW,
              "TUW-TUO-002-002",
              options(
                  "Felicia", "felicia@email.com", "5", "Thank you for the great experience!", "n"),
              ADD_EXPERT_REVIEW,
              "ARWR-CHC-002-003",
              options("Eve", "4", "Lots of rapids, very scary, watch out!", "y"),
              DISPLAY_REVIEWS,
              "SSB-TRG-002-001",
              DISPLAY_REVIEWS,
              "TUW-TUO-002-002",
              DISPLAY_REVIEWS,
              "ARWR-CHC-002-003",
              EXIT));

      assertContains("There is 1 review for activity 'Nemos Playground'.");
      assertContains("* [3/5] Public review (SSB-TRG-002-001-R1) by 'Alice'");
      assertContains("\"Could be better\"");
      assertContains("There is 1 review for activity 'Close Encounters of the Lake'.");
      assertContains("* [5/5] Private review (TUW-TUO-002-002-R1) by 'Felicia'");
      assertContains("Resolved: \"-\"");
      assertContains("\"Thank you for the great experience!\"");
      assertContains("There is 1 review for activity 'River Rush'.");
      assertContains("* [4/5] Expert review (ARWR-CHC-002-003-R1) by 'Eve'");
      assertContains("\"Lots of rapids, very scary, watch out!\"");
      assertContains("Recommended by experts.");
      assertDoesNotContain("There are", true);
      assertDoesNotContain("Need to email", true);
    }

    @Test
    public void T3_11_public_review_endorsed_afterwards() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PUBLIC_REVIEW,
              "SSB-TRG-002-001",
              options("Alice", "n", "3", "Could be better"),
              ENDORSE_REVIEW,
              "SSB-TRG-002-001-R1",
              DISPLAY_REVIEWS,
              "SSB-TRG-002-001",
              EXIT));

      assertContains("Review 'SSB-TRG-002-001-R1' endorsed successfully.");
      assertContains("There is 1 review for activity 'Nemos Playground'.");
      assertContains("* [3/5] Public review (SSB-TRG-002-001-R1) by 'Alice'");
      assertContains("\"Could be better\"");
      assertContains("Endorsed by admin.");
      assertDoesNotContain("There are", true);
    }

    @Test
    public void T3_12_resolve_private_review_not_found() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PRIVATE_REVIEW,
              "TUW-TUO-002-002",
              options("Felicia", "felicia@email.com", "1", "I'm so disappointed!", "y"),
              RESOLVE_REVIEW,
              "TUW-TUO-002-002-R2",
              "'So sorry to hear that!'",
              DISPLAY_REVIEWS,
              "TUW-TUO-002-002",
              EXIT));
      assertContains("Review not found: 'TUW-TUO-002-002-R2' is an invalid review ID.");
      assertDoesNotContain("resolved successfully", true);
    }

    @Test
    public void T3_13_resolve_private_review() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PRIVATE_REVIEW,
              "TUW-TUO-002-002",
              options("Felicia", "felicia@email.com", "1", "I'm so disappointed!", "y"),
              RESOLVE_REVIEW,
              "TUW-TUO-002-002-R1",
              "'So sorry to hear that!'",
              DISPLAY_REVIEWS,
              "TUW-TUO-002-002",
              EXIT)); // HIDE

      assertContains("Review 'TUW-TUO-002-002-R1' resolved successfully.");
      assertContains("There is 1 review for activity 'Close Encounters of the Lake'.");
      assertContains("* [1/5] Private review (TUW-TUO-002-002-R1) by 'Felicia'");
      assertDoesNotContain("Need to email", true);
    }

    @Test
    public void T3_14_upload_image_expert_review_not_found() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              UPLOAD_REVIEW_IMAGE,
              "ARWR-CHC-002-003-R2",
              "image.jpg",
              EXIT));

      assertContains("Review not found: 'ARWR-CHC-002-003-R2' is an invalid review ID.");
      assertDoesNotContain("uploaded successfully for review", true);
      assertDoesNotContain("Images: ", true);
    }

    @Test
    public void T3_15_upload_image_expert_review() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_EXPERT_REVIEW,
              "ARWR-CHC-002-003",
              options("Eve", "4", "Lots of rapids, very scary, watch out!", "y"),
              UPLOAD_REVIEW_IMAGE,
              "ARWR-CHC-002-003-R1",
              "image.jpg",
              DISPLAY_REVIEWS,
              "ARWR-CHC-002-003",
              EXIT));

      assertContains("Image 'image.jpg' uploaded successfully for review 'ARWR-CHC-002-003-R1'.");
      assertContains("There is 1 review for activity 'River Rush'.");
      assertContains("* [4/5] Expert review (ARWR-CHC-002-003-R1) by 'Eve'");
      assertContains("Images: [image.jpg]");
      assertDoesNotContain("There are", true);
    }

    @Test
    public void T3_16_upload_multiple_images_expert_review() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_EXPERT_REVIEW,
              "ARWR-CHC-002-003",
              options("Eve", "4", "Lots of rapids, very scary, watch out!", "y"),
              UPLOAD_REVIEW_IMAGE,
              "ARWR-CHC-002-003-R1",
              "image1.jpg",
              UPLOAD_REVIEW_IMAGE,
              "ARWR-CHC-002-003-R1",
              "image2.jpg",
              DISPLAY_REVIEWS,
              "ARWR-CHC-002-003",
              EXIT));

      assertContains("Image 'image1.jpg' uploaded successfully for review 'ARWR-CHC-002-003-R1'.");
      assertContains("Image 'image2.jpg' uploaded successfully for review 'ARWR-CHC-002-003-R1'.");

      assertContains("There is 1 review for activity 'River Rush'.");
      assertContains("* [4/5] Expert review (ARWR-CHC-002-003-R1) by 'Eve'");
      assertContains("Images: [image1.jpg,image2.jpg]");
      assertDoesNotContain("There are", true);
    }

    @Test
    public void T3_17_rank_activities_no_reviews() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, CREATE_27_ACTIVITIES, DISPLAY_TOP_ACTIVITIES, EXIT));

      assertContains("No reviewed activities found in Auckland | Tāmaki Makaurau.");
      assertContains("No reviewed activities found in Hamilton | Kirikiriroa.");
      assertContains("No reviewed activities found in Tauranga.");
      assertContains("No reviewed activities found in Taupo | Taupō-nui-a-Tia.");
      assertContains("No reviewed activities found in Wellington | Te Whanganui-a-Tara.");
      assertContains("No reviewed activities found in Nelson | Whakatu.");
      assertContains("No reviewed activities found in Christchurch | Ōtautahi.");
      assertContains("No reviewed activities found in Dunedin | Ōtepoti.");
      assertDoesNotContain("Top reviewed activity in ", true);
    }

    @Test
    public void T3_18_rank_activities_one_review() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PUBLIC_REVIEW,
              "SSB-TRG-002-001",
              options("Alice", "n", "3", "Could be better"),
              DISPLAY_TOP_ACTIVITIES,
              EXIT));

      assertContains(
          "Top reviewed activity in Tauranga is 'Nemos Playground',"
              + " with an average"
              + " rating"
              + " of 3"); // not caring about decimals
    }

    @Test
    public void T3_19_rank_activities_two_reviews_different_activities_same_location()
        throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PUBLIC_REVIEW,
              "SSB-TRG-002-001",
              options("Alice", "n", "3", "Could be better"),
              ADD_PUBLIC_REVIEW,
              "SSB-TRG-002-002",
              options("Bob", "n", "4", "Good"),
              DISPLAY_TOP_ACTIVITIES,
              EXIT));

      assertContains(
          "Top reviewed activity in Tauranga is 'Seaside Mussel Munch', with an average"
              + " rating"
              + " of 4");

      assertDoesNotContain("in Tauranga is 'Nemos Playground'", true);
    }

    @Test
    public void T3_20_rank_activities_multiple_reviews_per_activity_same_location()
        throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_27_ACTIVITIES,
              ADD_PUBLIC_REVIEW,
              "SSB-TRG-002-001",
              options("Alice", "n", "1", "OK"),
              ADD_PUBLIC_REVIEW,
              "SSB-TRG-002-001",
              options("Bob", "n", "2", "OK"),
              ADD_EXPERT_REVIEW,
              "SSB-TRG-002-001",
              options("Charlie", "3", "OK", "y"),
              ADD_PUBLIC_REVIEW,
              "SSB-TRG-002-002",
              options("Alice", "n", "3", "Nice"),
              ADD_EXPERT_REVIEW,
              "SSB-TRG-002-002",
              options("Charlie", "5", "Nice", "y"),
              DISPLAY_REVIEWS,
              "SSB-TRG-002-001",
              DISPLAY_REVIEWS,
              "SSB-TRG-002-002",
              DISPLAY_TOP_ACTIVITIES,
              EXIT));

      assertContains(
          "Top reviewed activity in Tauranga is 'Seaside Mussel Munch', with an average rating of"
              + " 4");
      assertDoesNotContain("in Tauranga is 'Nemos Playground'", true);
    }
  }

  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  public static class YourTests extends CliTest {

    public YourTests() {
      super(Main.class);
    }

    @Override
    public void reset() {}

    @Test
    public void T4_C1_01_lowercase_operator_name() throws Exception {
      runCommands(
        CREATE_OPERATOR,
        "'the place'",
        "'AKL'",
        SEARCH_OPERATORS,
        "*",
        EXIT
      );

      assertContains("* the place ('TP-AKL-001' located in 'Auckland | Tāmaki Makaurau')");
    }

    @Test
    public void T4_C1_02_short_operator_name() throws Exception {
      runCommands(
        CREATE_OPERATOR,
        "'TP'",
        "'AKL'",
        EXIT
      );
      
      assertContains("not a valid operator name.");
    }

    @Test
    public void T4_C1_03_matching_name_location_search() throws Exception {
      runCommands(
        CREATE_OPERATOR,
        "'akl test scene'",
        "trg",
        CREATE_OPERATOR,
        "'unrelated test scene'",
        "akl",
        SEARCH_OPERATORS,
        "akl",
        EXIT
      );
      
      assertContains("There are 2 matching operators found:");
    }

    @Test
    public void T4_C1_04_macron_location_search_create() throws Exception {
      runCommands(
        CREATE_OPERATOR,
        "'Generic Operator Name'",
        "'Tāmaki Makaurau'",
        SEARCH_OPERATORS,
        "'Tāmaki Makaurau'",
        EXIT
      );
      
      assertContains("Successfully created operator");
      // ending bracket denotes message from search function
      assertContains("located in 'Auckland | Tāmaki Makaurau')");
    }

    @Test // not sure the correct protocol to handle this...
    public void T4_C1_05_operator_startswith_special_character() throws Exception {
      runCommands(
        CREATE_OPERATOR,
        "'!Generic -Name'",
        "AKL",
        SEARCH_OPERATORS,
        "AKL",
        EXIT
      );
      
      assertContains("Successfully created operator");
      // assertContains("located in 'Auckland | Tāmaki Makaurau')");
    }

    @Test
    public void T4_C1_06_operator_name_double_space() throws Exception {
      runCommands(
        CREATE_OPERATOR,
        "'Doubled  space  Operator'",
        "akl",
        SEARCH_OPERATORS,
        "AKL",
        EXIT
      );
      
      assertContains("Successfully created operator");
    }

    @Test
    public void T4_C1_07_operator_name_thai() throws Exception {
      runCommands(
        CREATE_OPERATOR,
        "'การทดลอง'",
        "akl",
        EXIT
      );
      
      assertContains("Successfully created operator");
    }

    @Test
    public void T4_C1_08_location_search_multiple_match() throws Exception {
      runCommands(
        CREATE_OPERATOR,
        "'Microcosm'",
        "wlg",
        CREATE_OPERATOR,
        "'Macrocosm'",
        "nsn",
        SEARCH_OPERATORS,
        "e",
        EXIT
      );
      
      assertContains("There are 2 matching operators found:");
    }

    @Test
    public void T4_C2_01_invalid_review_rating_low() throws Exception {
      runCommands(
        CREATE_OPERATOR,
        "'United Alliance'",
        "akl",
        CREATE_ACTIVITY,
        "'Ambiguous Ambiguity'",
        "other",
        "ua-akl-001",
        ADD_PUBLIC_REVIEW,
        "ua-akl-001-001",
        options("TestUser", "n", "0", "comment"),
        DISPLAY_REVIEWS,
        "ua-akl-001-001",
        EXIT
      );
      
      assertContains("[1/5]");
    }

    @Test
    public void T4_C2_02_invalid_review_rating_high() throws Exception {
      runCommands(
        CREATE_OPERATOR,
        "'United Alliance'",
        "akl",
        CREATE_ACTIVITY,
        "'Ambiguous Ambiguity'",
        "other",
        "ua-akl-001",
        ADD_PUBLIC_REVIEW,
        "ua-akl-001-001",
        options("TestUser", "n", "999", "comment"),
        DISPLAY_REVIEWS,
        "ua-akl-001-001",
        EXIT
      );
      
      assertContains("[5/5]");
    }

    @Test
    public void T4_C2_03_case_sensitive_display_review() throws Exception {
      runCommands(
        CREATE_OPERATOR,
        "'United Alliance'",
        "akl",
        CREATE_ACTIVITY,
        "'Ambiguous Ambiguity'",
        "other",
        "ua-akl-001",
        ADD_PUBLIC_REVIEW,
        "ua-akl-001-001",
        options("TestUser", "n", "999", "comment"),
        DISPLAY_REVIEWS,
        "ua-akl-001-001",
        EXIT
      );
      
      assertContains("(UA-AKL-001-001-R1)");
    }
  }

  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  public static class CustomTask2 extends CliTest {

    public CustomTask2() {
      super(Main.class);
    }

    @Override
    public void reset() {}

    @Test
    public void T2_C01_view_activities_single() throws Exception {
      // testing VIEW_ACTIVITIES works for a single activity within an operator.
      runCommands(
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-001'",
          VIEW_ACTIVITIES, "'SGO-TRG-001'",
          EXIT);

      // in relation to expected output
      assertContains("There is 1 matching activity found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-001-001/Food] offered by Some Generic Operator");
      
      // creation should be successful, plurality addressed
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("There are", true);
    }

    @Test
    public void T2_C02_view_activities_2() throws Exception {
      // testing VIEW_ACTIVITIES works for 2 activities within an operator.
      runCommands(
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-001'",
          CREATE_ACTIVITY, "'A Second Activity'", "'Culture'", "'SGO-TRG-001'",
          VIEW_ACTIVITIES, "'SGO-TRG-001'",
          EXIT);

      // in relation to expected output
      assertContains("There are 2 matching activities found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-001-001/Food] offered by Some Generic Operator");
          assertContains(
            "* A Second Activity: [SGO-TRG-001-002/Culture] offered by Some Generic Operator");
      
      // creation should be successful, plurality addressed
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("There is", true);
    }

    @Test
    public void T2_C03_view_activities_more() throws Exception {
      // testing VIEW_ACTIVITIES works for more than 2 activities within an operator.
      // indirectly also tests for all activity types correctly displayed.
      runCommands(
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-001'",
          CREATE_ACTIVITY, "'A Second Activity'", "'Culture'", "'SGO-TRG-001'",
          CREATE_ACTIVITY, "'A Third Activity'", "'Adventure'", "'SGO-TRG-001'",
          CREATE_ACTIVITY, "'A Fourth Activity'", "'Food'", "'SGO-TRG-001'",
          CREATE_ACTIVITY, "'A Fifth Activity'", "'Wildlife'", "'SGO-TRG-001'",
          CREATE_ACTIVITY, "'Another Activity'", "'Other'", "'SGO-TRG-001'",
          VIEW_ACTIVITIES, "'SGO-TRG-001'",
          EXIT);

      // expected output
      assertContains("There are 6 matching activities found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-001-001/Food] offered by Some Generic Operator");
      assertContains(
          "* A Second Activity: [SGO-TRG-001-002/Culture] offered by Some Generic Operator");
      assertContains(
          "* A Third Activity: [SGO-TRG-001-003/Adventure] offered by Some Generic Operator");
      assertContains(
          "* A Fourth Activity: [SGO-TRG-001-004/Food] offered by Some Generic Operator");
      assertContains(
          "* A Fifth Activity: [SGO-TRG-001-005/Wildlife] offered by Some Generic Operator");
      assertContains(
          "* Another Activity: [SGO-TRG-001-006/Other] offered by Some Generic Operator");

      
      // creation should be successful, plurality addressed
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("There is", true);
    }

    @Test
    public void T2_C04_view_activities_case_insensitivity() throws Exception {
      // testing VIEW_ACTIVITIES works a case-insensitive operator-id.
      runCommands(
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-001'",
          VIEW_ACTIVITIES, "'sgo-trg-001'",
          EXIT);

      // in relation to expected output
      assertContains("There is 1 matching activity found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-001-001/Food] offered by Some Generic Operator");

      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("Operator not found:", true);
      assertDoesNotContain("There are no activities found.", true);
    }

    @Test
    public void T2_C05_view_activities_input_whitespace() throws Exception {
      // testing VIEW_ACTIVITIES works with whitespace surrounding the actual input.
      runCommands(
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-001'",
          VIEW_ACTIVITIES, "'             SGO-TRG-001           '",
          EXIT);

      // in relation to expected output
      assertContains("There is 1 matching activity found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-001-001/Food] offered by Some Generic Operator");

      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("Operator not found:", true);
      assertDoesNotContain("There are no activities found.", true);
    }

    @Test
    public void T2_C06_create_activity_insensitive_activity_type() throws Exception {
      // testing CREATE_ACTIVITY works with a case-insensitive activity type.
      runCommands(
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'food'", "'SGO-TRG-001'",
          CREATE_ACTIVITY, "'The 2nd Activity For The Generic Operator'", "'FOOD'", "'SGO-TRG-001'",
          VIEW_ACTIVITIES, "'SGO-TRG-001'",
          EXIT);

      // in relation to expected output
      assertContains("There are 2 matching activities found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-001-001/Food] offered by Some Generic Operator");
      assertContains(
          "* The 2nd Activity For The Generic Operator: [SGO-TRG-001-002/Food] offered by Some Generic Operator");
  
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("Operator not found:", true);
      assertDoesNotContain("There are no activities found.", true);
    }

    @Test
    public void T2_C07_create_activity_insensitive_operator_id() throws Exception {
      // testing CREATE_ACTIVITY works with a case-insensitive operator-id.
      runCommands(
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'food'", "'sgo-trg-001'",
          VIEW_ACTIVITIES, "'SGO-TRG-001'",
          EXIT);

      // in relation to expected output
      assertContains("There is 1 matching activity found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-001-001/Food] offered by Some Generic Operator");
      
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("Operator not found:", true);
      assertDoesNotContain("There are no activities found.", true);
    }

    @Test
    public void T2_C08_create_activity_auto_other_type() throws Exception {
      // testing CREATE_ACTIVITY converts unknown activity types to 'Other'.
      runCommands(
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Tedium'", "'SGO-TRG-001'",
          VIEW_ACTIVITIES, "'SGO-TRG-001'",
          EXIT);

      // in relation to expected output
      assertContains("There is 1 matching activity found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-001-001/Other] offered by Some Generic Operator");
      
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("Operator not found:", true);
      assertDoesNotContain("There are no activities found.", true);
    }

    @Test
    public void T2_C09_create_activity_whitespace() throws Exception {
      // testing CREATE_ACTIVITY works with whitespace surrounding the actual inputs.
      runCommands(
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'   The Activity For The Generic Operator'     ", "'          Food   '", "'  SGO-TRG-001        '",
          VIEW_ACTIVITIES, "'SGO-TRG-001'",
          EXIT);

      // in relation to expected output
      assertContains("There is 1 matching activity found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-001-001/Food] offered by Some Generic Operator");
      
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("Operator not found:", true);
      assertDoesNotContain("There are no activities found.", true);
    }

    @Test
    public void T2_C10_create_activity_whitespace_char_min() throws Exception {
      // testing CREATE_ACTIVITY correctly determines short activity names.
      runCommands(
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'  Or   '", "'Food'", "'SGO-TRG-001'",
          EXIT);

      // in relation to expected output
      assertContains("Activity not created: 'Or' is not a valid activity name");
      assertDoesNotContain("Successfully created activity", true);
      assertDoesNotContain("Operator not found:", true);
    }

    @Test
    public void T2_C11_view_activities_whitespace_char_min() throws Exception {
      // testing VIEW_ACTIVITIES only outputs its own activities.
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-003'",
          VIEW_ACTIVITIES, "'SGO-TRG-003'",
          EXIT));

      // in relation to expected output
      assertContains("There is 1 matching activity found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-003-001/Food] offered by Some Generic Operator");
      
      // creation should be successful, plurality addressed
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("There are", true);
    }

    @Test
    public void T2_C12_search_activities_english_location() throws Exception {
      // testing SEARCH_ACTIVITIES works for searching an english location
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          SEARCH_ACTIVITIES, "Nelson",
          EXIT));

      assertContains("There are 2 matching activities found:");
      assertContains(
          "* Stars or Spaceships?: [NUW-NSN-001-001/Scenic] offered by Nelson UFO Watching");
      assertContains(
          "* Meteorites & Meat Pies: [NUW-NSN-001-002/Food] offered by Nelson UFO Watching");
      assertDoesNotContain("zero", true);
      assertDoesNotContain("There is 1", true);
    }

    @Test
    public void T2_C13_search_activities_abbrev_location() throws Exception {
      // testing SEARCH_ACTIVITIES works for searching an abbreviated location
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          SEARCH_ACTIVITIES, "NSN",
          EXIT));

      assertContains("There are 2 matching activities found:");
      assertContains(
          "* Stars or Spaceships?: [NUW-NSN-001-001/Scenic] offered by Nelson UFO Watching");
      assertContains(
          "* Meteorites & Meat Pies: [NUW-NSN-001-002/Food] offered by Nelson UFO Watching");
      assertDoesNotContain("zero", true);
      assertDoesNotContain("There is 1", true);
    }

    @Test
    public void T2_C14_search_activities_whitespace() throws Exception {
      // testing SEARCH_ACTIVITIES works when arguments have surrounding whitespace
      runCommands(
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'         The Activity For The Generic Operator          '", "'       Food       '", "'       SGO-TRG-001    '",
          SEARCH_ACTIVITIES, "'Activity'",
          EXIT);

      assertContains("There is 1 matching activity found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-001-001/Food] offered by Some Generic Operator");
      
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("There are", true);
    }

    @Test
    public void T2_C15_search_activities_whitespace() throws Exception {
      // testing SEARCH_ACTIVITIES works when keyword has surrounding whitespace
      runCommands(
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-001'",
          SEARCH_ACTIVITIES, "'        Activity         '",
          EXIT);

      assertContains("There is 1 matching activity found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-001-001/Food] offered by Some Generic Operator");
      
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("There are", true);
    }

    @Test
    public void T2_C16_search_activities_name_insensitive() throws Exception {
      // testing SEARCH_ACTIVITIES accepts case-insensitive activity names
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-003'",
          SEARCH_ACTIVITIES, "'the activity for the generic operator'",
          EXIT));

      assertContains("There is 1 matching activity found:");
      assertContains(
          "* The Activity For The Generic Operator: [SGO-TRG-003-001/Food] offered by Some Generic Operator");
      
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("There are", true);
    }

    @Test
    public void T2_C17_search_activities_type_insensitive() throws Exception {
      // testing SEARCH_ACTIVITIES accepts case-insensitive activity types
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-003'",
          SEARCH_ACTIVITIES, "'food'",
          EXIT));

      assertContains("There are 11 matching activities found:");
      assertContains(
          "  * Flaming Feast: [VBJ-AKL-002-001/Food] offered by Volcano Bungee Jump");
      assertContains(
          "  * Whale and Chips: [MWWW-HLZ-001-002/Food] offered by Mystical Waikato Whale Watching");
      assertContains(
          "  * The Gandalf Picnic: [HST-HLZ-002-002/Food] offered by Hobbiton Skydiving Tours");
      assertContains(
          "  * Seaside Mussel Munch: [SSB-TRG-002-002/Food] offered by Shark Snorkel Bay");
      assertContains(
          "  * Waterfall Wine Tasting: [HFBR-TUO-001-001/Food] offered by Huka Falls Barrel Rides");
      assertContains(
          "  * Unidentified Frying Objects: [TUW-TUO-002-001/Food] offered by Taupo UFO Watching");
      assertContains(
          "  * Meteorites & Meat Pies: [NUW-NSN-001-002/Food] offered by Nelson UFO Watching");
      assertContains(
          "  * Wild Desert Desserts: [CCT-CHC-001-001/Food] offered by Christchurch Camel Treks");
      assertContains(
          "  * Rapid Riverside Ramen: [ARWR-CHC-002-001/Food] offered by Avon River Whitewater Rafting");
      assertContains(
          "  * Penguin Pies: [DPP-DUD-001-001/Food] offered by Dunedin Penguin Parade");
      assertContains(
          "  * The Activity For The Generic Operator: [SGO-TRG-003-001/Food] offered by Some Generic Operator");

      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("There is", true);
    }

    @Test
    public void T2_C18_search_activities_location_insensitive() throws Exception {
      // testing SEARCH_ACTIVITIES accepts case-insensitive location names
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-003'",
          SEARCH_ACTIVITIES, "'tAURANGA'",
          EXIT));

      assertContains("There are 4 matching activities found:");
      assertContains(
          "  * Legends of the Lost Snow: [MMSR-TRG-001-001/Culture] offered by Mount Maunganui Ski Resort");
      assertContains(
          "  * Nemos Playground: [SSB-TRG-002-001/Wildlife] offered by Shark Snorkel Bay");
      assertContains(
          "  * Seaside Mussel Munch: [SSB-TRG-002-002/Food] offered by Shark Snorkel Bay");
      assertContains(
          "  * The Activity For The Generic Operator: [SGO-TRG-003-001/Food] offered by Some Generic Operator");
      
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("There is", true);
    }

    @Test
    public void T2_C19_search_activities_activity_type_substring() throws Exception {
      // testing SEARCH_ACTIVITIES works for substring of activity type
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-003'",
          SEARCH_ACTIVITIES, "'Fo'",
          EXIT));

      assertContains("There are 11 matching activities found:");
      assertContains(
          "  * Flaming Feast: [VBJ-AKL-002-001/Food] offered by Volcano Bungee Jump");
      assertContains(
          "  * Whale and Chips: [MWWW-HLZ-001-002/Food] offered by Mystical Waikato Whale Watching");
      assertContains(
          "  * The Gandalf Picnic: [HST-HLZ-002-002/Food] offered by Hobbiton Skydiving Tours");
      assertContains(
          "  * Seaside Mussel Munch: [SSB-TRG-002-002/Food] offered by Shark Snorkel Bay");
      assertContains(
          "  * Waterfall Wine Tasting: [HFBR-TUO-001-001/Food] offered by Huka Falls Barrel Rides");
      assertContains(
          "  * Unidentified Frying Objects: [TUW-TUO-002-001/Food] offered by Taupo UFO Watching");
      assertContains(
          "  * Meteorites & Meat Pies: [NUW-NSN-001-002/Food] offered by Nelson UFO Watching");
      assertContains(
          "  * Wild Desert Desserts: [CCT-CHC-001-001/Food] offered by Christchurch Camel Treks");
      assertContains(
          "  * Rapid Riverside Ramen: [ARWR-CHC-002-001/Food] offered by Avon River Whitewater Rafting");
      assertContains(
          "  * Penguin Pies: [DPP-DUD-001-001/Food] offered by Dunedin Penguin Parade");
      assertContains(
          "  * The Activity For The Generic Operator: [SGO-TRG-003-001/Food] offered by Some Generic Operator");

      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("There is", true);
    }

    @Test
    public void T2_C20_search_activities_location_substring() throws Exception {
      // testing SEARCH_ACTIVITIES works for substring of activity type
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-003'",
          SEARCH_ACTIVITIES, "'Taur'",
          EXIT));

      assertContains("There are 4 matching activities found:");
      assertContains(
          "  * Legends of the Lost Snow: [MMSR-TRG-001-001/Culture] offered by Mount Maunganui Ski Resort");
      assertContains(
          "  * Nemos Playground: [SSB-TRG-002-001/Wildlife] offered by Shark Snorkel Bay");
      assertContains(
          "  * Seaside Mussel Munch: [SSB-TRG-002-002/Food] offered by Shark Snorkel Bay");
      assertContains(
          "  * The Activity For The Generic Operator: [SGO-TRG-003-001/Food] offered by Some Generic Operator");
      
      assertDoesNotContain("Activity not created:", true);
      assertDoesNotContain("There is", true);
    }

    @Test
    public void T2_C21_search_activities_operator_name() throws Exception {
      // testing SEARCH_ACTIVITIES works as expected for operator name
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          CREATE_OPERATOR,"'Some Generic Operator'", "'TRG'",
          CREATE_ACTIVITY, "'The Activity For The Generic Operator'", "'Food'", "'SGO-TRG-003'",
          SEARCH_ACTIVITIES, "'Some Generic Operator'",
          EXIT));

      assertContains("There are no matching activities found.");
      
      assertDoesNotContain("  * ", true);
      assertDoesNotContain("There is", true);
    }
  }

  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  public static class CustomTask3 extends CliTest {

    public CustomTask3() {
      super(Main.class);
    }

    @Override
    public void reset() {}

    @Test
    public void T3_C01_display_reviews_invalid_id() throws Exception {
      // testing DISPLAY_REVIEWS correctly handles invalid ids
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          DISPLAY_REVIEWS, "'NONE-AKL-999'",
          EXIT));

      assertContains("Activity not found: 'NONE-AKL-999' is an invalid activity ID.");
      
      assertDoesNotContain("  * ", true);
      assertDoesNotContain("There are no reviews for activity", true);
    }

    @Test
    public void T3_C02_display_reviews_non_plural() throws Exception {
      // testing DISPLAY_REVIEWS correctly prints a singular review
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "5", "T'was quite good!"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("There is 1 review for activity");
      
      assertDoesNotContain(" reviews for activity", true); // catches 0 and 2+
    }

    @Test
    public void T3_C03_display_reviews_case_insensitive() throws Exception {
      // testing DISPLAY_REVIEWS id input is case-insensitive
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "5", "T'was quite good!"),
          DISPLAY_REVIEWS, "'wact-akl-001-001'",
          EXIT));

      assertContains("There is 1 review for activity");
      
      assertDoesNotContain(" reviews for activity", true);
      assertDoesNotContain("Activity not found: ", true);
    }

    @Test
    public void T3_C04_display_reviews_multiple_reviews_same_activity() throws Exception {
      // testing DISPLAY_REVIEWS correctly display multiple reviews for the same activity
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "5", "T'was quite good!"),
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("Barcode", "n", "4", "Pretty cool!"),
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("Maxicode", "n", "3", "Kinda just meh."),
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("Aztec Code", "n", "2", "This sucked."),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("There are 4 reviews for activity");
      assertContains("  * [5/5] Public review (WACT-AKL-001-001-R1) by 'QR Code'");
      assertContains("    \"T'was quite good!\" ");
      assertContains("  * [4/5] Public review (WACT-AKL-001-001-R2) by 'Barcode'");
      assertContains("    \"Pretty cool!\" ");
      assertContains("  * [3/5] Public review (WACT-AKL-001-001-R3) by 'Maxicode'");
      assertContains("    \"Kinda just meh.\" ");
      assertContains("  * [2/5] Public review (WACT-AKL-001-001-R4) by 'Aztec Code'");
      assertContains("    \"This sucked.\" ");
      
      assertDoesNotContain("Review not added:", true);
      assertDoesNotContain("Activity not found: ", true);
    }

    @Test
    public void T3_C05_display_reviews_whitespace() throws Exception {
      // testing DISPLAY_REVIEWS id input handles surrounding whitespace
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "5", "T'was quite good!"),
          DISPLAY_REVIEWS, "'      WACT-AKL-001-001          '",
          EXIT));

      assertContains("There is 1 review for activity");
      
      assertDoesNotContain(" reviews for activity", true);
      assertDoesNotContain("Activity not found: ", true);
    }

    @Test
    public void T3_C06_public_review_anonymous() throws Exception {
      // testing ADD_PUBLIC_REVIEW correctly implements anonymity
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "y", "5", "T'was quite good!"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("There is 1 review for activity");
      assertContains("by 'Anonymous'");
      
      assertDoesNotContain(" reviews for activity", true);
      assertDoesNotContain("Activity not found: ", true);
      assertDoesNotContain("by 'QR Code'", true);
    }

    @Test
    public void T3_C07_public_review_whitespace() throws Exception {
      // testing ADD_PUBLIC_REVIEW correctly handles surrounding whitespace
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'   WACT-AKL-001-001   '", options("QR Code", "y", "5", "T'was quite good!"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Public review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
    }

    @Test
    public void T3_C08_public_review_case_insensitive() throws Exception {
      // testing ADD_PUBLIC_REVIEW handling case-insensitivity
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'wact-akl-001-001'", options("QR Code", "n", "5", "T'was quite good!"),
          EXIT));

      assertContains("Public review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
    }

    @Test
    public void T3_C09_public_review_lower_bound() throws Exception {
      // testing ADD_PUBLIC_REVIEW addresses out of bound ratings
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "-2", "T'was quite good!"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Public review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");
      assertContains("There is 1 review for activity");
      assertContains("  * [1/5] Public review (WACT-AKL-001-001-R1) by 'QR Code'");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
      assertDoesNotContain("  * [-2/5] Public review (WACT-AKL-001-001-R1) by 'QR Code'", true);
    }

    @Test
    public void T3_C10_public_review_upper_bound() throws Exception {
      // testing ADD_PUBLIC_REVIEW addresses out of bound ratings
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "20", "T'was quite good!"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Public review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");
      assertContains("There is 1 review for activity");
      assertContains("  * [5/5] Public review (WACT-AKL-001-001-R1) by 'QR Code'");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
      assertDoesNotContain("  * [20/5] Public review (WACT-AKL-001-001-R1) by 'QR Code'", true);
    }

    @Test
    public void T3_C11_private_review_followup() throws Exception {
      // testing ADD_PRIVATE_REVIEW correctly displays when followup is required.
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PRIVATE_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "qr.code2025@something.com", "5", "Illegitimatism?", "y"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("There is 1 review for activity");
      assertContains("by 'QR Code'");
      assertContains("Need to email");
      
      assertDoesNotContain(" reviews for activity", true);
      assertDoesNotContain("Activity not found: ", true);
      assertDoesNotContain("Resolved:", true);
    }

    @Test
    public void T3_C12_private_review_invalid_activity() throws Exception {
      // testing ADD_PRIVATE_REVIEW handles invalid activity ids
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PRIVATE_REVIEW, "'NONE-AKL-999-001'", options("QR Code", "qr.code2025@something.com", "5", "Illegitimatism?", "y"),
          EXIT));

      assertContains("Review not added: ");
      assertContains("is an invalid activity ID.");
      
      assertDoesNotContain("added successfully for activity", true);
    }

    @Test
    public void T3_C13_private_review_whitespace() throws Exception {
      // testing ADD_PRIVATE_REVIEW handles surrounding whitespace
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PRIVATE_REVIEW, "'      WACT-AKL-001-001      '", options("QR Code", "qr.code2025@something.com", "5", "Illegitimatism?", "y"),
          EXIT));

      assertContains("Private review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
    }

    @Test
    public void T3_C14_private_review_case_insensitive() throws Exception {
      // testing ADD_PRIVATE_REVIEW handling case-insensitivity
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PRIVATE_REVIEW, "'wact-akl-001-001'", options("QR Code", "qr.code2025@something.com", "5", "Illegitimatism?", "y"),
          EXIT));

      assertContains("Private review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
    }

    @Test
    public void T3_C15_private_review_lower_bound() throws Exception {
      // testing ADD_PRIVATE_REVIEW addresses out of bound ratings
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PRIVATE_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "qr.code2025@something.com", "0", "Illegitimatism?", "n"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Private review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");
      assertContains("There is 1 review for activity");
      assertContains("  * [1/5] Private review (WACT-AKL-001-001-R1) by 'QR Code'");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
      assertDoesNotContain("  * [0/5] Private review (WACT-AKL-001-001-R1) by 'QR Code'", true);
    }

    @Test
    public void T3_C16_private_review_upper_bound() throws Exception {
      // testing ADD_PRIVATE_REVIEW addresses out of bound ratings
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PRIVATE_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "qr.code2025@something.com", "15", "Illegitimatism?", "n"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Private review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");
      assertContains("There is 1 review for activity");
      assertContains("  * [5/5] Private review (WACT-AKL-001-001-R1) by 'QR Code'");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
      assertDoesNotContain("  * [15/5] Private review (WACT-AKL-001-001-R1) by 'QR Code'", true);
    }

    @Test
    public void T3_C17_expert_review_invalid_id() throws Exception {
      // testing ADD_EXPERT_REVIEW handles invalid activity ids
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_EXPERT_REVIEW, "'NONE-AKL-999-001'", options("QR Code", "5", "T'was quite good!", "y"),
          EXIT));

      assertContains("Review not added: ");
      assertContains("is an invalid activity ID.");
      
      assertDoesNotContain("added successfully for activity", true);
    }

    @Test
    public void T3_C18_expert_review_not_recommend() throws Exception {
      // testing ADD_EXPERT_REVIEW and DISPLAY_REVIEWS correctly doesn't print recommendation line.
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_EXPERT_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "5", "T'was quite good!", "n"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Expert review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");
      assertContains("  * [5/5] Expert review (WACT-AKL-001-001-R1) by 'QR Code'");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
      assertDoesNotContain("Recommended by experts.", true);
    }

    @Test
    public void T3_C19_expert_review_no_image() throws Exception {
      // testing ADD_EXPERT_REVIEW and DISPLAY_REVIEWS printing no images
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_EXPERT_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "5", "T'was quite good!", "y"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Expert review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");
      assertContains("  * [5/5] Expert review (WACT-AKL-001-001-R1) by 'QR Code'");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
      assertDoesNotContain("Images:", true);
    }

    @Test
    public void T3_C20_expert_review_whitespace() throws Exception {
      // testing ADD_EXPERT_REVIEW and DISPLAY_REVIEWS printing no images
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_EXPERT_REVIEW, "'   WACT-AKL-001-001     '", options("QR Code", "5", "T'was quite good!", "y"),
          EXIT));

      assertContains("Expert review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
    }

    @Test
    public void T3_C21_expert_review_case_insensitive() throws Exception {
      // testing ADD_EXPERT_REVIEW handling case-insensitivity
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_EXPERT_REVIEW, "'wact-akl-001-001'", options("QR Code", "5", "T'was quite good!", "y"),
          EXIT));

      assertContains("Expert review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
    }

    @Test
    public void T3_C22_expert_review_lower_bound() throws Exception {
      // testing ADD_EXPERT_REVIEW addresses out of bound ratings
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_EXPERT_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "-999", "T'was quite miserable!", "n"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Expert review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");
      assertContains("There is 1 review for activity");
      assertContains("  * [1/5] Expert review (WACT-AKL-001-001-R1) by 'QR Code'");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
      assertDoesNotContain("  * [-999/5] Expert review (WACT-AKL-001-001-R1) by 'QR Code'", true);
    }

    @Test
    public void T3_C23_expert_review_upper_bound() throws Exception {
      // testing ADD_EXPERT_REVIEW addresses out of bound ratings
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_EXPERT_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "999", "T'was quite good!", "y"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Expert review 'WACT-AKL-001-001-R1");
      assertContains("added successfully for activity ");
      assertContains("There is 1 review for activity");
      assertContains("  * [5/5] Expert review (WACT-AKL-001-001-R1) by 'QR Code'");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
      assertDoesNotContain("  * [999/5] Expert review (WACT-AKL-001-001-R1) by 'QR Code'", true);
    }

    @Test
    public void T3_C24_all_reviews_consistent_id() throws Exception {
      // testing different review types correctly increment review id for one activity
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "5", "T'was quite good!"),
          ADD_PRIVATE_REVIEW, "'WACT-AKL-001-001'", options("Barcode", "bar.code2029@something.com", "4", "Illegitimatism?", "n"),
          ADD_EXPERT_REVIEW, "'WACT-AKL-001-001'", options("Maxicode", "3", "Kinda just meh.", "y"),
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("There are 3 reviews for activity");
      assertContains("  * [5/5] Public review (WACT-AKL-001-001-R1) by 'QR Code'");
      assertContains("    \"T'was quite good!\" ");
      assertContains("  * [4/5] Private review (WACT-AKL-001-001-R2) by 'Barcode'");
      assertContains("    \"Illegitimatism?\" ");
      assertContains("  * [3/5] Expert review (WACT-AKL-001-001-R3) by 'Maxicode'");
      assertContains("    \"Kinda just meh.\" ");

      assertDoesNotContain("Review not added: ", true);
      assertDoesNotContain("is an invalid activity ID.", true);
      assertDoesNotContain("  * [4/5] Private review (WACT-AKL-001-001-R1) by 'Barcode'", true);
      assertDoesNotContain("  * [3/5] Expert review (WACT-AKL-001-001-R1) by 'Maxicode'", true);
    }

    @Test
    public void T3_C25_endorse_review_invalid_id() throws Exception {
      // testing ENDORSE_REVIEW handles invalid review id
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ENDORSE_REVIEW, "'WACT-AKL-001-001-R1'",
          EXIT));

      assertContains("Review not found:");

      assertDoesNotContain("endorsed successfully.", true);
    }

    @Test
    public void T3_C26_endorse_review_invalid_review_type() throws Exception {
      // testing ENDORSE_REVIEW handles wrong review type
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PRIVATE_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "qr.code2025@something.com", "5", "Illegitimatism?", "y"),
          ENDORSE_REVIEW, "'WACT-AKL-001-001-R1'",
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Review not endorsed: 'WACT-AKL-001-001-R1' is not a public review.");

      assertDoesNotContain("Review not found:", true);
      assertDoesNotContain("endorsed successfully.", true);
      assertDoesNotContain("Endorsed by admin.", true);
    }

    @Test
    public void T3_C27_endorse_review_whitespace() throws Exception {
      // testing ENDORSE_REVIEW handles surrounding whitespace
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "5", "T'was quite good!"),
          ENDORSE_REVIEW, "'             WACT-AKL-001-001-R1               '",
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Review 'WACT-AKL-001-001-R1' endorsed successfully.");
      assertContains("Endorsed by admin.");

      assertDoesNotContain("Review not found:", true);
      assertDoesNotContain(" is not a public review.", true);
    }

    @Test
    public void T3_C28_endorse_review_case_insensitive() throws Exception {
      // testing ENDORSE_REVIEW handles case-insensitivity
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "5", "T'was quite good!"),
          ENDORSE_REVIEW, "'wact-akl-001-001-r1'",
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Review 'WACT-AKL-001-001-R1' endorsed successfully.");
      assertContains("Endorsed by admin.");

      assertDoesNotContain("Review not found:", true);
      assertDoesNotContain(" is not a public review.", true);
    }

    @Test
    public void T3_C29_resolve_review_invalid_review_type() throws Exception {
      // testing RESOLVE_REVIEW handles wrong review type
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "5", "T'was quite good!"),
          RESOLVE_REVIEW, "'WACT-AKL-001-001-R1'", "'Poacher.'",
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Review not resolved: 'WACT-AKL-001-001-R1' is not a private review.");
      assertContains("Public review 'WACT-AKL-001-001-R1");

      assertDoesNotContain("Review not found:", true);
      assertDoesNotContain("resolved successfully.", true);
      assertDoesNotContain("Resolved: \"Poacher.\"", true);
    }

    @Test
    public void T3_C30_resolve_review_whitespace() throws Exception {
      // testing RESOLVE_REVIEW handles surrounding whitespace
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PRIVATE_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "qr.code2025@something.com", "5", "Illegitimatism?", "y"),
          RESOLVE_REVIEW, "'             WACT-AKL-001-001-R1               '", "'Poacher.'",
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Review 'WACT-AKL-001-001-R1' resolved successfully.");
      assertContains("Resolved: \"Poacher.\"");

      assertDoesNotContain("Review not found:", true);
      assertDoesNotContain(" is not a private review.", true);
    }

    @Test
    public void T3_C31_resolve_review_case_insensitive() throws Exception {
      // testing RESOLVE_REVIEW handles case-insensitivity
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PRIVATE_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "qr.code2025@something.com", "5", "Illegitimatism?", "y"),
          RESOLVE_REVIEW, "'wact-akl-001-001-r1'", "'Poacher.'",
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Review 'WACT-AKL-001-001-R1' resolved successfully.");
      assertContains("Resolved: \"Poacher.\"");

      assertDoesNotContain("Review not found:", true);
      assertDoesNotContain(" is not a private review.", true);
    }

    @Test
    public void T3_C32_add_image_invalid_review_type() throws Exception {
      // testing UPLOAD_REVIEW_IMAGE handles wrong review type
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "5", "T'was quite good!"),
          UPLOAD_REVIEW_IMAGE, "'WACT-AKL-001-001-R1'", "hunger.png",
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("Image not uploaded: 'WACT-AKL-001-001-R1' is not an expert review.");
      assertContains("Public review 'WACT-AKL-001-001-R1");

      assertDoesNotContain("Review not found:", true);
      assertDoesNotContain("uploaded successfully", true);
      assertDoesNotContain("Images: [hunger.png]", true);
    }

    @Test
    public void T3_C33_add_image_review_whitespace() throws Exception {
      // testing UPLOAD_REVIEW_IMAGE handles surrounding whitespace
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_EXPERT_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "5", "T'was quite good!", "y"),
          UPLOAD_REVIEW_IMAGE, "'     WACT-AKL-001-001-R1     '", "hunger.png",
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("uploaded successfully for review 'WACT-AKL-001-001-R1'.");
      assertContains("Images: [hunger.png]");

      assertDoesNotContain("Review not found:", true);
      assertDoesNotContain(" is not an expert review.", true);
    }

    @Test
    public void T3_C34_add_image_case_insensitive() throws Exception {
      // testing UPLOAD_REVIEW_IMAGE handles case-insensitivity
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_EXPERT_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "5", "T'was quite good!", "y"),
          UPLOAD_REVIEW_IMAGE, "'wact-akl-001-001-r1'", "hunger.png",
          DISPLAY_REVIEWS, "'WACT-AKL-001-001'",
          EXIT));

      assertContains("uploaded successfully for review 'WACT-AKL-001-001-R1'.");
      assertContains("Images: [hunger.png]");

      assertDoesNotContain("Review not found:", true);
      assertDoesNotContain(" is not an expert review.", true);
    }

    @Test
    public void T3_C35_top_activities_average_calc() throws Exception {
      // testing activity review averages match expected for non-integer result
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "5", "T'was quite good!"),
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("Barcode", "n", "2", "Pretty Cool!"),
          ADD_EXPERT_REVIEW, "'WACT-AKL-001-001'", options("Maxicode", "3", "Kinda just meh.", "y"),
          DISPLAY_TOP_ACTIVITIES,
          EXIT));

      assertContains("'Bethells Beach Camel Trek', with an average rating of 3.3");

      assertDoesNotContain("'Bethells Beach Camel Trek', with an average rating of 3.0", true);
      assertDoesNotContain("'Bethells Beach Camel Trek', with an average rating of 4.0", true);
    }

    @Test
    public void T3_C36_top_activities_skip_private_average() throws Exception {
      // testing activity review averages skip private reviews in calculation
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("QR Code", "n", "5", "T'was quite good!"),
          ADD_PRIVATE_REVIEW, "'WACT-AKL-001-001'", options("Barcode", "bar.code2029@something.com", "1", "Illegitimatism?", "y"),
          ADD_EXPERT_REVIEW, "'WACT-AKL-001-001'", options("Maxicode", "3", "Kinda just meh.", "y"),
          DISPLAY_TOP_ACTIVITIES,
          EXIT));

      assertContains("'Bethells Beach Camel Trek', with an average rating of 4.0");

      assertDoesNotContain("'Bethells Beach Camel Trek', with an average rating of 2.6", true);
      assertDoesNotContain("'Bethells Beach Camel Trek', with an average rating of 2.7", true);
      assertDoesNotContain("'Bethells Beach Camel Trek', with an average rating of 3.0", true);
    }

    @Test
    public void T3_C37_top_activities_skip_private_display() throws Exception {
      // testing DISPLAY_TOP_ACTIVITIES skip private reviews in displaying top activities
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          ADD_PRIVATE_REVIEW, "'WACT-AKL-001-001'", options("Barcode", "bar.code2029@something.com", "1", "Illegitimatism?", "y"),
          DISPLAY_TOP_ACTIVITIES,
          EXIT));

      assertContains("No reviewed activities found in Auckland | Tāmaki Makaurau");

      assertDoesNotContain("with an average rating of", true);
    }

    @Test
    public void T3_C38_top_activities_multiple_locations_multiple_activities_single_review() throws Exception {
      // testing DISPLAY_TOP_ACTIVITIES correctly allocates reviews for locations
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          // expected passes
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("Wisper", "n", "5", "..."),
          ADD_EXPERT_REVIEW, "'MWWW-HLZ-001-001'", options("Corsola", "3", "^^^", "y"),
          ADD_PUBLIC_REVIEW, "'MMSR-TRG-001-001'", options("Hämis", "n", "2", "<>"),
          ADD_PUBLIC_REVIEW, "'HFBR-TUO-001-001'", options("Leshy", "n", "4", "vvv"),
          ADD_PUBLIC_REVIEW, "'PBJ-WLG-001-001'", options("Solael", "n", "2", "!!!"),
          ADD_PUBLIC_REVIEW, "'NUW-NSN-001-001'", options("Azar", "n", "3", "N/A"),
          ADD_PUBLIC_REVIEW, "'ARWR-CHC-002-001'", options("Cadence", "n", "5", "---"),
          ADD_PUBLIC_REVIEW, "'DPP-DUD-001-002'", options("Lucky", "n", "3", "///"),

          // expected overwrites
          ADD_PUBLIC_REVIEW, "'VBJ-AKL-002-001'", options("Orbide", "n", "4", "..."),
          ADD_EXPERT_REVIEW, "'HST-HLZ-002-001'", options("Garbodor", "2", "^^^", "y"),
          ADD_PUBLIC_REVIEW, "'SSB-TRG-002-001'", options("Sauvojen Tuntija", "n", "1", "<>"),
          ADD_PUBLIC_REVIEW, "'TUW-TUO-002-001'", options("Shamura", "n", "2", "vvv"),
          ADD_PUBLIC_REVIEW, "'PBJ-WLG-001-002'", options("Korvaak", "n", "1", "!!!"),
          ADD_PUBLIC_REVIEW, "'NUW-NSN-001-002'", options("Narhan", "n", "2", "N/A"),
          ADD_PUBLIC_REVIEW, "'ARWR-CHC-002-003'", options("Coda", "n", "1", "---"),
          ADD_PUBLIC_REVIEW, "'DPP-DUD-001-003'", options("Richard", "n", "1", "///"),

          DISPLAY_TOP_ACTIVITIES,
          EXIT));

      assertContains("Top reviewed activity in Auckland | Tāmaki Makaurau is 'Bethells Beach Camel Trek', with an average rating of 5.0");
      assertContains("Top reviewed activity in Hamilton | Kirikiriroa is 'Whale and Dolphin Safari', with an average rating of 3.0");
      assertContains("Top reviewed activity in Tauranga is 'Legends of the Lost Snow', with an average rating of 2.0");
      assertContains("Top reviewed activity in Taupo | Taupō-nui-a-Tia is 'Waterfall Wine Tasting', with an average rating of 4.0");
      assertContains("Top reviewed activity in Wellington | Te Whanganui-a-Tara is 'Jumping Through Political Loopholes', with an average rating of 2.0");
      assertContains("Top reviewed activity in Nelson | Whakatu is 'Stars or Spaceships?', with an average rating of 3.0");
      assertContains("Top reviewed activity in Christchurch | Ōtautahi is 'Rapid Riverside Ramen', with an average rating of 5.0");
      assertContains("Top reviewed activity in Dunedin | Ōtepoti is 'Waddling Wonders', with an average rating of 3.0");


      assertDoesNotContain("No reviewed activities found", true);
    }

    @Test
    public void T3_C39_top_activities_multiple_locations_multiple_activities_multiple_reviews() throws Exception {
      // testing DISPLAY_TOP_ACTIVITIES correctly allocates reviews and averages (all public reviews)
      runCommands(unpack(
          CREATE_14_OPERATORS,
          CREATE_27_ACTIVITIES,
          CREATE_OPERATOR, "Some Wellington Operator", "WLG",
          CREATE_ACTIVITY, "Generic Activity", "OTHER", "SWO-WLG-002",
          // expected passes
          // 4.33
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("Wisper", "n", "5", "..."),
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("Snugget", "n", "4", "..."),
          ADD_PUBLIC_REVIEW, "'WACT-AKL-001-001'", options("Squeem", "n", "4", "..."),

          // 3.67
          ADD_EXPERT_REVIEW, "'MWWW-HLZ-001-001'", options("Corsola", "5", "^^^", "y"),
          ADD_EXPERT_REVIEW, "'MWWW-HLZ-001-001'", options("Espurr", "3", "^^^", "y"),
          ADD_EXPERT_REVIEW, "'MWWW-HLZ-001-001'", options("Wishiwashi", "3", "^^^", "y"),

          // 4.67
          ADD_PUBLIC_REVIEW, "'MMSR-TRG-001-001'", options("Hämis", "n", "5", "<>"),
          ADD_PUBLIC_REVIEW, "'MMSR-TRG-001-001'", options("Märkiäinen", "n", "4", "<>"),
          ADD_PUBLIC_REVIEW, "'MMSR-TRG-001-001'", options("Sähikäismenninkkäinen", "n", "5", "<>"),

          // 4.00
          ADD_PUBLIC_REVIEW, "'HFBR-TUO-001-001'", options("Leshy", "n", "4", "vvv"),
          ADD_PUBLIC_REVIEW, "'HFBR-TUO-001-001'", options("Shamura", "n", "4", "vvv"),

          // 4.33
          ADD_PUBLIC_REVIEW, "'PBJ-WLG-001-001'", options("Solael", "n", "3", "!!!"),
          ADD_PUBLIC_REVIEW, "'PBJ-WLG-001-001'", options("Bysmiel", "n", "5", "!!!"),
          ADD_PUBLIC_REVIEW, "'PBJ-WLG-001-001'", options("Dreeg", "n", "5", "!!!"),

          // 4.25
          ADD_PUBLIC_REVIEW, "'NUW-NSN-001-001'", options("Azar", "n", "5", "N/A"),
          ADD_PUBLIC_REVIEW, "'NUW-NSN-001-001'", options("Lian", "n", "5", "N/A"),
          ADD_PUBLIC_REVIEW, "'NUW-NSN-001-001'", options("Ironheart", "n", "2", "N/A"),
          ADD_PUBLIC_REVIEW, "'NUW-NSN-001-001'", options("Pressel", "n", "5", "N/A"),

          // 4.75
          ADD_PUBLIC_REVIEW, "'ARWR-CHC-002-001'", options("Cadence", "n", "5", "---"),
          ADD_PUBLIC_REVIEW, "'ARWR-CHC-002-001'", options("Melody", "n", "5", "---"),
          ADD_PUBLIC_REVIEW, "'ARWR-CHC-002-001'", options("Aria", "n", "4", "---"),
          ADD_PUBLIC_REVIEW, "'ARWR-CHC-002-001'", options("Dorian", "n", "5", "---"),

          // 4.00
          ADD_PUBLIC_REVIEW, "'DPP-DUD-001-002'", options("Lucky", "n", "3", "///"),
          ADD_PUBLIC_REVIEW, "'DPP-DUD-001-002'", options("Cole", "n", "4", "///"),
          ADD_PUBLIC_REVIEW, "'DPP-DUD-001-002'", options("Haley", "n", "5", "///"),

          // expected overwrites
          // 3.75
          ADD_PUBLIC_REVIEW, "'VBJ-AKL-002-001'", options("Orbide", "n", "4", "..."),
          ADD_PUBLIC_REVIEW, "'VBJ-AKL-002-001'", options("Oogler", "n", "4", "..."),
          ADD_PUBLIC_REVIEW, "'VBJ-AKL-002-001'", options("Sporgus", "n", "4", "..."),
          ADD_PUBLIC_REVIEW, "'VBJ-AKL-002-001'", options("Taroni", "n", "3", "..."),

          // 2.33
          ADD_EXPERT_REVIEW, "'HST-HLZ-002-001'", options("Garbodor", "2", "^^^", "y"),
          ADD_EXPERT_REVIEW, "'HST-HLZ-002-001'", options("Muk", "4", "^^^", "y"),
          ADD_EXPERT_REVIEW, "'HST-HLZ-002-001'", options("Gengar", "1", "^^^", "y"),

          // 2.25
          ADD_PUBLIC_REVIEW, "'SSB-TRG-002-001'", options("Sauvojen Tuntija", "n", "1", "<>"),
          ADD_PUBLIC_REVIEW, "'SSB-TRG-002-001'", options("Helvetinkatse", "n", "1", "<>"),
          ADD_PUBLIC_REVIEW, "'SSB-TRG-002-001'", options("Muodonmuutosmestari", "n", "3", "<>"),
          ADD_PUBLIC_REVIEW, "'SSB-TRG-002-001'", options("Ukkoshyypiö", "n", "4", "<>"),

          // 3.2
          ADD_PUBLIC_REVIEW, "'SSB-TRG-002-002'", options("Limatoukka", "n", "4", "<>"),
          ADD_PUBLIC_REVIEW, "'SSB-TRG-002-002'", options("Haavoittajamestari", "n", "4", "<>"),
          ADD_PUBLIC_REVIEW, "'SSB-TRG-002-002'", options("Hahmonvaihtaja", "n", "5", "<>"),
          ADD_PUBLIC_REVIEW, "'SSB-TRG-002-002'", options("Hohtonaamio", "n", "2", "<>"),
          ADD_PUBLIC_REVIEW, "'SSB-TRG-002-002'", options("Toimari", "n", "1", "<>"),

          // 2.67
          ADD_PUBLIC_REVIEW, "'TUW-TUO-002-001'", options("Kallamar", "n", "3", "vvv"),
          ADD_PUBLIC_REVIEW, "'TUW-TUO-002-001'", options("Heket", "n", "3", "vvv"),
          ADD_PUBLIC_REVIEW, "'TUW-TUO-002-001'", options("Narinder", "n", "2", "vvv"),

          // 3.00
          ADD_PUBLIC_REVIEW, "'PBJ-WLG-001-002'", options("Loghorrean", "n", "1", "!!!"),
          ADD_PUBLIC_REVIEW, "'PBJ-WLG-001-002'", options("Korvaak", "n", "4", "!!!"),
          ADD_PUBLIC_REVIEW, "'PBJ-WLG-001-002'", options("Theodin", "n", "4", "!!!"),

          // 3.67
          ADD_PUBLIC_REVIEW, "'SWO-WLG-002-001'", options("Crate", "n", "5", "!!!"),
          ADD_PUBLIC_REVIEW, "'SWO-WLG-002-001'", options("Callagadra", "n", "4", "!!!"),
          ADD_PUBLIC_REVIEW, "'SWO-WLG-002-001'", options("Rashalga", "n", "2", "!!!"),

          // 3.75
          ADD_PUBLIC_REVIEW, "'NUW-NSN-001-002'", options("Narhan", "n", "5", "N/A"),
          ADD_PUBLIC_REVIEW, "'NUW-NSN-001-002'", options("Momori", "n", "4", "N/A"),
          ADD_PUBLIC_REVIEW, "'NUW-NSN-001-002'", options("Hein", "n", "1", "N/A"),
          ADD_PUBLIC_REVIEW, "'NUW-NSN-001-002'", options("Phoenix", "n", "5", "N/A"),

          // 4.67
          ADD_PUBLIC_REVIEW, "'CCT-CHC-001-001'", options("Coda", "n", "1", "---"),
          ADD_PUBLIC_REVIEW, "'CCT-CHC-001-001'", options("Bolt", "n", "1", "---"),
          ADD_PUBLIC_REVIEW, "'CCT-CHC-001-001'", options("Tempo", "n", "1", "---"),

          // 3.67
          ADD_PUBLIC_REVIEW, "'ARWR-CHC-002-003'", options("Chaunter", "n", "1", "---"),
          ADD_PUBLIC_REVIEW, "'ARWR-CHC-002-003'", options("Bard", "n", "1", "---"),
          ADD_PUBLIC_REVIEW, "'ARWR-CHC-002-003'", options("Mary", "n", "1", "---"),

          // 2.50
          ADD_PUBLIC_REVIEW, "'DPP-DUD-001-003'", options("Richard", "n", "1", "///"),
          ADD_PUBLIC_REVIEW, "'DPP-DUD-001-003'", options("Edega", "n", "1", "///"),

          DISPLAY_TOP_ACTIVITIES,
          EXIT));

      assertContains("Top reviewed activity in Auckland | Tāmaki Makaurau is 'Bethells Beach Camel Trek', with an average rating of 4.3");
      assertContains("Top reviewed activity in Hamilton | Kirikiriroa is 'Whale and Dolphin Safari', with an average rating of 3.7");
      assertContains("Top reviewed activity in Tauranga is 'Legends of the Lost Snow', with an average rating of 4.7");
      assertContains("Top reviewed activity in Taupo | Taupō-nui-a-Tia is 'Waterfall Wine Tasting', with an average rating of 4.0");
      assertContains("Top reviewed activity in Wellington | Te Whanganui-a-Tara is 'Jumping Through Political Loopholes', with an average rating of 4.3");
      assertContains("Top reviewed activity in Nelson | Whakatu is 'Stars or Spaceships?', with an average rating of 4.3");
      assertContains("Top reviewed activity in Christchurch | Ōtautahi is 'Rapid Riverside Ramen', with an average rating of 4.8");
      assertContains("Top reviewed activity in Dunedin | Ōtepoti is 'Waddling Wonders', with an average rating of 4.0");


      assertDoesNotContain("No reviewed activities found", true);
    }
  }

  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  public static class OthersTestsTask1 extends CliTest {

    public OthersTestsTask1() {
      super(Main.class);
    }

    @Override
    public void reset() {}

    @Test
    public void T5_01_create_operator_saved() throws Exception {
      runCommands(
          CREATE_OPERATOR, "'West Auckland Camel Treks'", "'AKL'", SEARCH_OPERATORS, "*", EXIT);

      assertContains("There is 1 matching operator found:");
      assertContains(
          "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')");
      assertDoesNotContain("Operator not created", true);
      assertDoesNotContain("There are", true);
    }

    @Test
    public void T5_02_search_operators_capital_keyword() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "AUCKLAND", EXIT));

      assertContains("There are 2 matching operators found:");
      assertContains(
          "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')");
      assertContains(
          "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')");
      assertDoesNotContain("There is", true);
    }

    @Test
    public void T5_03_search_operators_capital_macron() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "Ā", EXIT));

      assertContains("There are 2 matching operators found:");
      assertContains(
          "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')");
      assertContains(
          "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')");
      assertDoesNotContain("There are no matching operators found.", true);
    }

    public void T5_04_search_operators_location_abbrev() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "CHC", EXIT));

      assertContains("There are 2 matching operators found:");
      assertContains(
          "* Christchurch Camel Treks ('CCT-CHC-001' located in 'Christchurch | Ōtautahi')");
      assertContains(
          "* Avon River Whitewater Rafting ('ARWR-CHC-002' located in 'Christchurch |"
              + " Ōtautahi')");
      assertDoesNotContain("There is", true);
      assertDoesNotContain("There are 14", true);
      assertDoesNotContain("There are no matching operators found.", true);
    }

    @Test
    public void T5_05_search_operators_garbage() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "qwertyuiop", EXIT));
      assertContains("There are no matching operators found.");
      assertDoesNotContain("found:", true);
    }

    @Test
    public void T5_06_search_operators_location_in_name() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_OPERATOR,
              "'Auckland Timeline'",
              "Wellington",
              SEARCH_OPERATORS,
              "auckland",
              EXIT));

      assertContains("There are 3 matching operators found:");
      assertContains(
          "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')");
      assertContains(
          "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')");
      assertContains(
          "* Auckland Timeline ('AT-WLG-002' located in 'Wellington | Te Whanganui-a-Tara')");
      assertDoesNotContain("There is", true);
      assertDoesNotContain("There are 2", true);
      assertDoesNotContain("There are 14", true);
      assertDoesNotContain("There are no matching operators found.", true);
    }

    @Test
    public void T5_07_search_operators_location_abbrev_in_name_and_keyword() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_OPERATOR,
              "'AKL Timeline'",
              "Wellington",
              SEARCH_OPERATORS,
              "akl",
              EXIT));

      assertContains("There are 3 matching operators found:");
      assertContains(
          "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')");
      assertContains(
          "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')");
      assertContains("* AKL Timeline ('AT-WLG-002' located in 'Wellington | Te Whanganui-a-Tara')");
      assertDoesNotContain("There is", true);
      assertDoesNotContain("There are 2", true);
      assertDoesNotContain("There are 14", true);
      assertDoesNotContain("There are no matching operators found.", true);
    }

    @Test
    public void T5_08_search_operators_location_abbrev_in_name_not_keyword() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_OPERATOR,
              "'AKL Timeline'",
              "Wellington",
              SEARCH_OPERATORS,
              "auckland",
              EXIT));

      assertContains("There are 2 matching operators found:");
      assertContains(
          "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')");
      assertContains(
          "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')");
      assertDoesNotContain(
          "* AKL Timeline ('AT-WLG-002' located in 'Wellington | Te Whanganui-a-Tara')", true);
      assertDoesNotContain("There is", true);
      assertDoesNotContain("There are 3", true);
      assertDoesNotContain("There are 14", true);
      assertDoesNotContain("There are no matching operators found.", true);
    }

    @Test
    public void T5_09_search_operators_location_abbrev_in_keyword_not_name() throws Exception {
      runCommands(
          unpack(
              CREATE_14_OPERATORS,
              CREATE_OPERATOR,
              "'Auckland Timeline'",
              "Wellington",
              SEARCH_OPERATORS,
              "akl",
              EXIT));

      assertContains("There are 2 matching operators found:");
      assertContains(
          "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')");
      assertContains(
          "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')");
      assertDoesNotContain(
          "* Auckland Timeline ('AT-WLG-002' located in 'Wellington | Te Whanganui-a-Tara')", true);
      assertDoesNotContain("There is", true);
      assertDoesNotContain("There are 3", true);
      assertDoesNotContain("There are 14", true);
      assertDoesNotContain("There are no matching operators found.", true);
    }

    @Test
    public void T5_10_create_operator_invalid_location() throws Exception {
      runCommands(
          CREATE_OPERATOR, "'Stewart Island Wild Kiwi Encounters'", "'Stewart Island'", EXIT);

      assertContains("Operator not created: 'Stewart Island' is an invalid location.");
      assertDoesNotContain("Successfully created operator");
    }

    @Test
    public void T5_11_create_operator_invalid_name() throws Exception {
      runCommands(CREATE_OPERATOR, "'Hi'", "'Auckland'", EXIT);

      assertContains("Operator not created: 'Hi' is not a valid operator name.");
      assertDoesNotContain("Successfully created operator");
    }

    @Test
    public void T5_12_search_operators_trailing_spaces_keyword() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "Auckland     ", EXIT));

      assertContains("There are 2 matching operators found:");
      assertContains(
          "* West Auckland Camel Treks ('WACT-AKL-001' located in 'Auckland | Tāmaki Makaurau')");
      assertContains(
          "* Volcano Bungee Jump ('VBJ-AKL-002' located in 'Auckland | Tāmaki Makaurau')");
      assertDoesNotContain("There is", true);
      assertDoesNotContain("There are 14", true);
      assertDoesNotContain("There are no matching operators found.", true);
    }

    @Test
    public void T5_13_search_operators_pipe_keyword() throws Exception {
      runCommands(unpack(CREATE_14_OPERATORS, SEARCH_OPERATORS, "|", EXIT));

      assertContains("There are no matching operators found.");
      assertDoesNotContain("There is", true);
      assertDoesNotContain("There are 14", true);
    }
  }

  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static class OthersTestsTask2 extends CliTest {

      public OthersTestsTask2() {
        super(Main.class);
      }

      // tests if the activity duplicate checks disregard spaces
      @Test
      public void T2_01_create_activity_invalid_activity_name() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'     Hi     '",
            "Adventure",
            "'WACT-AKL-001'",
            EXIT);
        assertContains("Activity not created");
        assertDoesNotContain("Successfully created activity", true);
      }

      // tests whether the activity name creation ignores spaces
      @Test
      public void T2_02_create_activity_extra_spaces_in_name() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'     Sunset Camel Trek     '",
            "Adventure",
            "'WACT-AKL-001'",
            EXIT);
        assertContains("Successfully created activity 'Sunset Camel Trek'");
        assertDoesNotContain("Successfully created activity '     Sunset Camel Trek     '", true);
      }

      // Tests if the other type is correctly utilised
      @Test
      public void T2_03_create_activity_unknown_activity_type() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'Sunset Camel Trek'",
            "ajsdoifjaoisdfjaosidfj",
            "'WACT-AKL-001'",
            EXIT);
        assertContains("Successfully created activity");
        assertContains("'Other'");
        assertDoesNotContain("Activity not created", true);
      }

      // Tests if the activity type is case insensitive
      @Test
      public void T2_04_create_activity_random_case_activity_type() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'Sunset Camel Trek'",
            "aDVeNTuRe",
            "'WACT-AKL-001'",
            EXIT);
        assertContains("Successfully created activity");
        assertContains("'Adventure'");
        assertDoesNotContain("Activity not created", true);
      }

      // Tests if the operator ID searching only searches IDs, and not the full operator details
      @Test
      public void T2_05_view_activity_weird_operator_name() throws Exception {
        runCommands(
            CREATE_OPERATOR, "'WACT-AKL-001'", "'AKL'", VIEW_ACTIVITIES, "'WACT-AKL-001'", EXIT);
        assertContains("Operator not found: 'WACT-AKL-001' is an invalid operator ID.");
        assertDoesNotContain("Successfully created activity", true);
        assertDoesNotContain("There are no matching activities found.");
      }

      // Tests if the activity searching includes searching for the english name of the location
      @Test
      public void T2_06_search_activities_found_keyword_location_english() throws Exception {
        runCommands(
            unpack(
                CREATE_14_OPERATORS, CREATE_27_ACTIVITIES, SEARCH_ACTIVITIES, "WELLINGton", EXIT));
        // assertContains("There are 2 matching activities found:");
        assertContains(
            "* Jumping Through Political Loopholes: [PBJ-WLG-001-001/Culture] offered by Parliament"
                + " Bungee Jump");
        assertContains(
            "* Politics with a View: [PBJ-WLG-001-002/Scenic] offered by Parliament Bungee Jump");
        assertDoesNotContain("There are no matching activities found.", true);
      }

      // Tests if the activity searching includes searching for the abbreviation of the location
      @Test
      public void T2_07_search_activities_found_keyword_location_abbreviation() throws Exception {
        runCommands(
            unpack(CREATE_14_OPERATORS, CREATE_27_ACTIVITIES, SEARCH_ACTIVITIES, "WlG", EXIT));
        // assertContains("There are 2 matching activities found:");
        assertContains(
            "* Jumping Through Political Loopholes: [PBJ-WLG-001-001/Culture] offered by Parliament"
                + " Bungee Jump");
        assertContains(
            "* Politics with a View: [PBJ-WLG-001-002/Scenic] offered by Parliament Bungee Jump");
        assertDoesNotContain("There are no matching activities found.", true);
      }

      // check if the activity searching includes searching for mixed capitalisation of special
      // characters
      @Test
      public void T2_08_search_activities_found_keyword_special_character() throws Exception {
        runCommands(
            unpack(
                CREATE_14_OPERATORS,
                CREATE_27_ACTIVITIES,
                SEARCH_ACTIVITIES,
                "'Ā'",
                // "Auckland",
                EXIT));
        assertContains("There are 4 matching activities found:");
        assertContains("Bethells Beach Camel Trek");
        assertContains("Sky Tower Base Jumping");
        assertContains("Flaming Feast");
        assertContains("Lava Lookout Walk");
      }

      // Checks if the activity searching matches the operator name
      @Test
      public void T2_09_search_activities_operator_name() throws Exception {
        runCommands(
            unpack(
                CREATE_14_OPERATORS,
                CREATE_27_ACTIVITIES,
                SEARCH_ACTIVITIES,
                "'West Auckland Camel Treks'",
                // "Auckland",
                EXIT));
        assertContains("There are no matching activities found.");
        assertDoesNotContain("found:");
      }

      // test whether the activity name is case sensitive when searching activities
      @Test
      public void T2_10_search_activities_activity_name_case_sensitive() throws Exception {
        runCommands(
            unpack(
                CREATE_14_OPERATORS,
                CREATE_27_ACTIVITIES,
                SEARCH_ACTIVITIES,
                "'bethells beach camel trek'",
                // "Auckland",
                EXIT));
        assertContains("There is 1 matching activity found:");
        assertContains("Bethells Beach Camel Trek");
        assertDoesNotContain("There are no matching activities found.", true);
      }

      // test whether the activity type is case sensitive when searching activities
      @Test
      public void T2_11_search_activities_activity_type_case_sensitive() throws Exception {
        runCommands(
            unpack(
                CREATE_14_OPERATORS,
                CREATE_27_ACTIVITIES,
                SEARCH_ACTIVITIES,
                "'adventURE'",
                // "Auckland",
                EXIT));

        assertContains("There are 5 matching activities found:");
        assertContains(
            "  * Bethells Beach Camel Trek: [WACT-AKL-001-001/Adventure] offered by West Auckland"
                + " Camel Treks");
        assertContains(
            "  * Sky Tower Base Jumping: [WACT-AKL-001-002/Adventure] offered by West Auckland"
                + " Camel Treks");
        assertContains(
            "  * The Frodo Jump: [HST-HLZ-002-001/Adventure] offered by Hobbiton Skydiving Tours");
        assertContains(
            "  * River Rush: [ARWR-CHC-002-003/Adventure] offered by Avon River Whitewater"
                + " Rafting");
        assertContains(
            "  * Snowy Slide: [DPP-DUD-001-003/Adventure] offered by Dunedin Penguin Parade");
        assertDoesNotContain("There are no matching activities found.", true);
      }

      // test whether searching activities with partial matching works
      @Test
      public void T2_12_search_activities_partial_matching() throws Exception {
        runCommands(
            unpack(
                CREATE_14_OPERATORS,
                CREATE_27_ACTIVITIES,
                SEARCH_ACTIVITIES,
                "'eaCH CAmel'",
                SEARCH_ACTIVITIES,
                "'aDVen'",
                SEARCH_ACTIVITIES,
                "dunED",
                EXIT));
        assertContains("There is 1 matching activity found:");
        assertContains(
            "  * Bethells Beach Camel Trek: [WACT-AKL-001-001/Adventure] offered by West Auckland"
                + " Camel Treks");
        assertContains("There are 5 matching activities found:");
        assertContains(
            "  * Bethells Beach Camel Trek: [WACT-AKL-001-001/Adventure] offered by West Auckland"
                + " Camel Treks");
        assertContains(
            "  * Sky Tower Base Jumping: [WACT-AKL-001-002/Adventure] offered by West Auckland"
                + " Camel Treks");
        assertContains(
            "  * The Frodo Jump: [HST-HLZ-002-001/Adventure] offered by Hobbiton Skydiving Tours");
        assertContains(
            "  * River Rush: [ARWR-CHC-002-003/Adventure] offered by Avon River Whitewater"
                + " Rafting");
        assertContains(
            "  * Snowy Slide: [DPP-DUD-001-003/Adventure] offered by Dunedin Penguin Parade");
        assertContains("There are 3 matching activities found:");
        assertContains("* Penguin Pies: [DPP-DUD-001-001/Food] offered by Dunedin Penguin Parade");
        assertContains(
            "  * Waddling Wonders: [DPP-DUD-001-002/Wildlife] offered by Dunedin Penguin Parade");
        assertContains(
            "  * Snowy Slide: [DPP-DUD-001-003/Adventure] offered by Dunedin Penguin Parade");
        assertDoesNotContain("There are no matching activities found.", true);
      }

      // Test whether the keyword is trimmed when searching for activities
      @Test
      public void T2_13_search_activities_surrounding_spaces() throws Exception {
        runCommands(
            unpack(
                CREATE_14_OPERATORS,
                CREATE_27_ACTIVITIES,
                SEARCH_ACTIVITIES,
                "'     Auckland     '",
                // "Auckland",
                EXIT));
        assertContains("There are 4 matching activities found:");
        assertContains("Bethells Beach Camel Trek");
        assertContains("Sky Tower Base Jumping");
        assertContains("Flaming Feast");
        assertContains("Lava Lookout Walk");
        assertDoesNotContain("There are no matching activities found.", true);
      }
    }

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static class OthersTestsTask3 extends CliTest {

      public OthersTestsTask3() {
        super(Main.class);
      }

      // this test ensures that when the activity ID is searched for, it only searches for IDs, and
      // doesn't return any activity that matches the keyword in a different field
      @Test
      public void T3_01_proper_activity_searching() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'Bethells Beach Camel Trek'",
            "Adventure",
            "'WACT-AKL-001'",
            CREATE_ACTIVITY,
            "'WACT-AKL-001-001'",
            "Adventure",
            "'WACT-AKL-001'",
            ADD_PUBLIC_REVIEW,
            "WACT-AKL-001-001",
            options("Alice", "n", "3", "Could be better"),
            DISPLAY_REVIEWS,
            "WACT-AKL-001-001",
            EXIT);
        assertContains("There is 1 review for activity 'Bethells Beach Camel Trek'.");
        assertDoesNotContain("There are no reviews for activity 'WACT-AKL-001-001'.");
      }

      // this test ensures that the rating limits (only from 1-5) are enforced, and that the rating
      // is saved
      @Test
      public void T3_02_proper_rating_limits() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'Bethells Beach Camel Trek'",
            "Adventure",
            "'WACT-AKL-001'",
            ADD_PUBLIC_REVIEW,
            "WACT-AKL-001-001",
            options("Alice", "n", "0", "I absolutely hated it! They scammed us!!1"),
            ADD_PRIVATE_REVIEW,
            "WACT-AKL-001-001",
            options("Bob", "bob@email.com", "10", "I loved it! 10/10", "n"),
            ADD_EXPERT_REVIEW,
            "WACT-AKL-001-001",
            options(
                "Clara",
                "6",
                "I enjoyed it, but the wait times were quite long, and the customer service wasn't"
                    + " the best, so my rating drops from a 8/10 to a 6/10",
                "n"),
            DISPLAY_REVIEWS,
            "WACT-AKL-001-001",
            EXIT);
        assertContains("[1/5] Public review (WACT-AKL-001-001-R1) by 'Alice'");
        assertContains("[5/5] Private review (WACT-AKL-001-001-R2) by 'Bob'");
        assertContains("[5/5] Expert review (WACT-AKL-001-001-R3) by 'Clara'");
        assertDoesNotContain("[0/5]");
        assertDoesNotContain("[10/5]");
        assertDoesNotContain("[55/5]");
      }

      // test that public reviews are anonymised only when needed
      @Test
      public void T3_03_add_public_review_anonymised() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'Bethells Beach Camel Trek'",
            "Adventure",
            "'WACT-AKL-001'",
            ADD_PUBLIC_REVIEW,
            "WACT-AKL-001-001",
            options("Alice", "y", "3", "Could be better"),
            ADD_PUBLIC_REVIEW,
            "WACT-AKL-001-001",
            options("Bob", "n", "4", "Good"),
            DISPLAY_REVIEWS,
            "WACT-AKL-001-001",
            EXIT);
        assertContains("[3/5] Public review (WACT-AKL-001-001-R1) by 'Anonymous'");
        assertContains("[4/5] Public review (WACT-AKL-001-001-R2) by 'Bob'");
        assertDoesNotContain("[3/5] Public review (WACT-AKL-001-001-R1) by 'Alice'");
        assertDoesNotContain("[4/5] Public review (WACT-AKL-001-001-R2) by 'Anonymous'");
      }

      // test that a public review does not start as endorsed
      @Test
      public void T3_XX_public_review_not_endorsed() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'Bethells Beach Camel Trek'",
            "Adventure",
            "'WACT-AKL-001'",
            ADD_PUBLIC_REVIEW,
            "WACT-AKL-001-001",
            options("Alice", "n", "3", "Could be better"),
            DISPLAY_REVIEWS,
            "WACT-AKL-001-001",
            EXIT);
        assertContains("[3/5] Public review (WACT-AKL-001-001-R1) by 'Alice'");
        assertDoesNotContain("Endorsed by admin.");
      }

      // test that the review being endorsed is a public review
      @Test
      public void T3_XX_endorse_review_not_public() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'Bethells Beach Camel Trek'",
            "Adventure",
            "WACT-AKL-001",
            ADD_PRIVATE_REVIEW,
            "WACT-AKL-001-001",
            options("Alice", "alice@mail.com", "3", "Could be better", "y"),
            ENDORSE_REVIEW,
            "WACT-AKL-001-001-R1",
            EXIT);
        assertContains("Review not endorsed: 'WACT-AKL-001-001-R1' is not a public review.");
        assertDoesNotContain("Review 'WACT-AKL-001-001-R1' endorsed successfully.");
      }

      // test that the correct error is printed when trying to endorse a non-existent review
      @Test
      public void T3_XX_endorse_review_not_found() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'Bethells Beach Camel Trek'",
            "Adventure",
            "'WACT-AKL-001'",
            ADD_PUBLIC_REVIEW,
            "WACT-AKL-001-001",
            options("Alice", "n", "3", "Could be better"),
            ENDORSE_REVIEW,
            "WACT-AKL-001-001-R2",
            EXIT);
        assertContains("Review not found: 'WACT-AKL-001-001-R2' is an invalid review ID.");
        assertDoesNotContain("Review 'WACT-AKL-001-001-R2' endorsed successfully.");
      }

      // test that private reviews that require a follow-up print the required message
      @Test
      public void T3_04_add_private_review_followup() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'Bethells Beach Camel Trek'",
            "Adventure",
            "'WACT-AKL-001'",
            ADD_PRIVATE_REVIEW,
            "WACT-AKL-001-001",
            options("Alice", "alice@mail.com", "3", "Could be better", "y"),
            DISPLAY_REVIEWS,
            "WACT-AKL-001-001",
            EXIT);
        assertContains("[3/5] Private review (WACT-AKL-001-001-R1) by 'Alice'");
        assertContains("Need to email 'alice@mail.com' for follow-up.");
        // see #390 to see why the below cannot be in the output
        // https://edstem.org/au/courses/19942/discussion/2587468
        assertDoesNotContain("Need to email 'felicia@email.com' for follow-up.");
        assertDoesNotContain("Resolved: \"-\"");
      }

      // test that the review being resolved on is a private review
      @Test
      public void T3_XX_resolve_review_not_private() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'Bethells Beach Camel Trek'",
            "Adventure",
            "'WACT-AKL-001'",
            ADD_PUBLIC_REVIEW,
            "WACT-AKL-001-001",
            options("Alice", "n", "3", "Could be better"),
            RESOLVE_REVIEW,
            "WACT-AKL-001-001-R1",
            "'So sorry to hear that!'",
            EXIT);
        assertContains("Review not resolved: 'WACT-AKL-001-001-R1' is not a private review.");
        assertDoesNotContain("Review 'WACT-AKL-001-001-R1' resolved successfully.");
      }

      // test that when an expert review does not recommend the activity, it doesn't state otherwise
      @Test
      public void T3_XX_expert_review_not_recommend() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'Bethells Beach Camel Trek'",
            "Adventure",
            "'WACT-AKL-001'",
            ADD_EXPERT_REVIEW,
            "WACT-AKL-001-001",
            options("Alice", "3", "Could be better", "n"),
            DISPLAY_REVIEWS,
            "WACT-AKL-001-001",
            EXIT);
        assertContains("[3/5] Expert review (WACT-AKL-001-001-R1) by 'Alice'");
        assertDoesNotContain("Recommended by experts.");
      }

      // test whether the review the image is being uploaded to is an expert review
      @Test
      public void T3_XX_upload_review_image_not_expert() throws Exception {
        runCommands(
            CREATE_OPERATOR,
            "'West Auckland Camel Treks'",
            "'AKL'",
            CREATE_ACTIVITY,
            "'Bethells Beach Camel Trek'",
            "Adventure",
            "'WACT-AKL-001'",
            ADD_PUBLIC_REVIEW,
            "WACT-AKL-001-001",
            options("Alice", "n", "3", "Could be better"),
            UPLOAD_REVIEW_IMAGE,
            "WACT-AKL-001-001-R1",
            "'image.png'",
            EXIT);
        assertContains("Image not uploaded: 'WACT-AKL-001-001-R1' is not an expert review.");
        assertDoesNotContain(
            "Image 'image.png' uploaded successfully for review 'WACT-AKL-001-001-R1'.");
      }

      // test if private and expert reviews check if the activity ID is valid
      @Test
      public void T3_XX_add_review_activity_id_invalid() throws Exception {
        runCommands(
            ADD_PRIVATE_REVIEW,
            "WACT-AKL-001-001",
            options("Felicia", "felicia@email.com", "5", "Great", "n"),
            ADD_EXPERT_REVIEW,
            "WACT-AKL-001-001",
            options("Eve", "4", "Good", "y"),
            EXIT);
        assertContains("Review not added: 'WACT-AKL-001-001' is an invalid activity ID.");
        assertDoesNotContain("Successfully created private review", true);
        assertDoesNotContain("Successfully created expert review", true);
      }

      // test if the activity ID is valid when displaying reviews
      @Test
      public void T3_XX_display_reviews_activity_id_invalid() throws Exception {
        runCommands(DISPLAY_REVIEWS, "WACT-AKL-001-001", EXIT);
        assertContains("Activity not found: 'WACT-AKL-001-001' is an invalid activity ID.");
        assertDoesNotContain("There are no reviews for activity 'WACT-AKL-001-001'.", true);
      }

      // test if the resolution to private reviews is set and displayed correctly
      @Test
      public void T3_XX_resolve_review_check_details() throws Exception {
        runCommands(
            unpack(
                CREATE_14_OPERATORS,
                CREATE_27_ACTIVITIES,
                ADD_PRIVATE_REVIEW,
                "WACT-AKL-001-001",
                options("Felicia", "felicia@email.com", "5", "Great", "y"),
                RESOLVE_REVIEW,
                "WACT-AKL-001-001-R1",
                "'So sorry to hear that!'",
                DISPLAY_REVIEWS,
                "WACT-AKL-001-001",
                EXIT));
        assertContains("[5/5] Private review (WACT-AKL-001-001-R1) by 'Felicia'");
        assertContains("Resolved: \"So sorry to hear that!\"");
        assertDoesNotContain("Resolved: \"-\"", true);
      }

      // test if private reviews being resolved don't print the error messages for not being private
      // and not having an invalid activity ID (basically just a hardcode detector)
      @Test
      public void T3_XX_resolve_review_unneeded_messages() throws Exception {
        runCommands(
            unpack(
                CREATE_14_OPERATORS,
                CREATE_27_ACTIVITIES,
                ADD_PRIVATE_REVIEW,
                "WACT-AKL-001-001",
                options("Felicia", "felicia@email.com", "5", "Great", "y"),
                RESOLVE_REVIEW,
                "WACT-AKL-001-001-R1",
                "'So sorry to hear that!'",
                DISPLAY_REVIEWS,
                "WACT-AKL-001-001",
                EXIT));
        assertContains("[5/5] Private review (WACT-AKL-001-001-R1) by 'Felicia'");
        assertContains("Resolved: \"So sorry to hear that!\"");
        assertDoesNotContain("Review not found: 'WACT-AKL-001-001-R1' is an invalid review ID.");
        assertDoesNotContain("Review not resolved: 'WACT-AKL-001-001-R1' is not a private review.");
      }

      // test that when public reviews are endorsed, it does not print the error message for not
      // being a public review, or having an invalid review ID
      @Test
      public void T3_XX_endorse_public_review() throws Exception {
        runCommands(
            unpack(
                CREATE_14_OPERATORS,
                CREATE_27_ACTIVITIES,
                ADD_PUBLIC_REVIEW,
                "WACT-AKL-001-001",
                options("Alice", "n", "3", "Could be better"),
                ENDORSE_REVIEW,
                "WACT-AKL-001-001-R1",
                EXIT));
        assertContains("Review 'WACT-AKL-001-001-R1' endorsed successfully.");
        assertDoesNotContain("Review not endorsed: 'WACT-AKL-001-001-R1' is not a public review.");
        assertDoesNotContain("Review not found: 'WACT-AKL-001-001-R1' is an invalid review ID.");
      }

      // test that when images are uploaded to private reviews, it does not print the error message
      // for not being an expert review, or the review not being found
      @Test
      public void T3_XX_upload_review_image_correct_messages() throws Exception {
        runCommands(
            unpack(
                CREATE_14_OPERATORS,
                CREATE_27_ACTIVITIES,
                ADD_EXPERT_REVIEW,
                "WACT-AKL-001-001",
                options("Eve", "4", "Good", "y"),
                UPLOAD_REVIEW_IMAGE,
                "WACT-AKL-001-001-R1",
                "'image.png'",
                EXIT));
        assertContains("Image 'image.png' uploaded successfully for review 'WACT-AKL-001-001-R1'.");
        assertDoesNotContain("Image not uploaded: 'WACT-AKL-001-001-R1' is not an expert review.");
        assertDoesNotContain("Review not found: 'WACT-AKL-001-001-R1' is an invalid review ID.");
      }

      // test that when there are no reviewed activities in any location, that all
      // locations print the same message (disregarding the location itself)
      @Test
      public void T3_XX_display_top_activities_no_reviews() throws Exception {
        runCommands(
            unpack(CREATE_14_OPERATORS, CREATE_27_ACTIVITIES, DISPLAY_TOP_ACTIVITIES, EXIT));

        assertContains("No reviewed activities found in Auckland | Tāmaki Makaurau.");
        assertContains("No reviewed activities found in Hamilton | Kirikiriroa.");
        assertContains("No reviewed activities found in Tauranga.");
        assertContains("No reviewed activities found in Taupo | Taupō-nui-a-Tia.");
        assertContains("No reviewed activities found in Wellington | Te Whanganui-a-Tara.");
        assertContains("No reviewed activities found in Nelson | Whakatu.");
        assertContains("No reviewed activities found in Christchurch | Ōtautahi.");
        assertContains("No reviewed activities found in Dunedin | Ōtepoti.");
        assertDoesNotContain("Top reviewed activity in", true);
      }

      // test if private reviews are discounted from the reviews
      @Test
      public void T3_XX_display_top_activities_private_reviews() throws Exception {
        runCommands(
            unpack(
                CREATE_14_OPERATORS,
                CREATE_27_ACTIVITIES,
                ADD_PRIVATE_REVIEW,
                "WACT-AKL-001-001",
                options("Felicia", "felicia@email.com", "5", "Great", "n"),
                DISPLAY_TOP_ACTIVITIES,
                EXIT));
        assertContains("No reviewed activities found in Auckland | Tāmaki Makaurau.");
        assertDoesNotContain("Top reviewed activity in Auckland | Tāmaki Makaurau:");
      }
    }

    
  

  private static final Object[] CREATE_14_OPERATORS =
      new Object[] {
        // *** Tāmaki Makaurau | Auckland ***
        CREATE_OPERATOR,
        "'West Auckland Camel Treks'", // WACT-AKL-001
        "'AKL'",
        CREATE_OPERATOR,
        "'Volcano Bungee Jump'", // VBJ-AKL-002
        "'AKL'",
        // *** Kirikiriroa | Hamilton ***
        CREATE_OPERATOR,
        "'Mystical Waikato Whale Watching'", // MWWW-HLZ-001
        "'HLZ'",
        CREATE_OPERATOR,
        "'Hobbiton Skydiving Tours'", // HST-HLZ-002
        "'HLZ'",
        // *** Tauranga ***
        CREATE_OPERATOR,
        "'Mount Maunganui Ski Resort'", // MMSR-TRG-001
        "'TRG'",
        CREATE_OPERATOR,
        "'Shark Snorkel Bay'", // SSB-TRG-002
        "'TRG'",
        // *** Taupō-nui-a-Tia | Taupo ***
        CREATE_OPERATOR,
        "'Huka Falls Barrel Rides'", // HFBR-TUO-001
        "'TUO'",
        CREATE_OPERATOR,
        "'Taupo UFO Watching'", // TUW-TUO-002
        "'TUO'",
        // *** Te Whanganui-a-Tara | Wellington ***
        CREATE_OPERATOR,
        "'Parliament Bungee Jump'", // PBJ-WLG-001
        "'WLG'",
        // *** Nelson | Whakatu ***
        CREATE_OPERATOR,
        "'Nelson UFO Watching'", // NUW-NSN-001
        "'NSN'",
        // *** Ōtautahi | Christchurch ***
        CREATE_OPERATOR,
        "'Christchurch Camel Treks'", // CCT-CHC-001
        "'CHC'",
        CREATE_OPERATOR,
        "'Avon River Whitewater Rafting'", // ARWR-CHC-002
        "'CHC'",
        // *** Ōtepoti | Dunedin ***
        CREATE_OPERATOR,
        "'Dunedin Penguin Parade'", // DPP-DUD-001
        "'DUD'",
        CREATE_OPERATOR,
        "'Baldwin Street Ski Jumping'", // BSSJ-DUD-002
        "'DUD'",
      };

  private static final Object[] CREATE_27_ACTIVITIES =
      new Object[] {
        // *** West Aucklad Camel Treks | Tāmaki Makaurau | Auckland ***
        CREATE_ACTIVITY, // 1
        "'Bethells Beach Camel Trek'",
        "Adventure",
        "'WACT-AKL-001'",
        CREATE_ACTIVITY, // 2
        "'Sky Tower Base Jumping'",
        "Adventure",
        "'WACT-AKL-001'",
        // *** Volcano Bungee Jump | Tāmaki Makaurau | Auckland ***
        CREATE_ACTIVITY, // 3
        "'Flaming Feast'",
        "Food",
        "'VBJ-AKL-002'",
        CREATE_ACTIVITY, // 4
        "'Lava Lookout Walk'",
        "SCENIC",
        "'VBJ-AKL-002'",
        // *** Mystical Waikato Whale Watching | Kirikiriroa | Hamilton ***
        CREATE_ACTIVITY, // 5
        "'Whale and Dolphin Safari'",
        "Wildlife",
        "'MWWW-HLZ-001'",
        CREATE_ACTIVITY, // 6
        "'Whale and Chips'",
        "Food",
        "'MWWW-HLZ-001'",
        // *** Hobbiton Skydiving Tours | Kirikiriroa | Hamilton ***
        CREATE_ACTIVITY, // 7
        "'The Frodo Jump'",
        "Adventure",
        "'HST-HLZ-002'",
        CREATE_ACTIVITY, // 8
        "'The Gandalf Picnic'",
        "Food",
        "'HST-HLZ-002'",
        CREATE_ACTIVITY, // 9
        "'Flying Orcs'",
        "Wildlife",
        "'HST-HLZ-002'",
        // *** Mount Maunganui Ski Resort | Tauranga ***
        CREATE_ACTIVITY, // 10
        "'Legends of the Lost Snow'",
        "Culture",
        "'MMSR-TRG-001'",
        // *** Shark Snorkel Bay | Tauranga ***
        CREATE_ACTIVITY, // 11
        "'Nemos Playground'",
        "Wildlife",
        "'SSB-TRG-002'",
        CREATE_ACTIVITY, // 12
        "'Seaside Mussel Munch'",
        "Food",
        "'SSB-TRG-002'",
        // *** Huka Falls Barrel Rides | Taupō-nui-a-Tia | Taupo ***
        CREATE_ACTIVITY, // 13
        "'Waterfall Wine Tasting'",
        "Food",
        "'HFBR-TUO-001'",
        CREATE_ACTIVITY, // 14
        "'Huka Eel Submarine'",
        "Wildlife",
        "'HFBR-TUO-001'",
        // *** Taupo UFO Watching | Taupō-nui-a-Tia | Taupo ***
        CREATE_ACTIVITY, // 15
        "'Unidentified Frying Objects'",
        "Food",
        "'TUW-TUO-002'",
        CREATE_ACTIVITY, // 16
        "'Close Encounters of the Lake'",
        "Wildlife",
        "'TUW-TUO-002'",
        // *** Parliament Bungee Jump | Te Whanganui-a-Tara | Wellington ***
        CREATE_ACTIVITY, // 17
        "'Jumping Through Political Loopholes'",
        "Culture",
        "'PBJ-WLG-001'",
        CREATE_ACTIVITY, // 18
        "'Politics with a View'",
        "SCENIC",
        "'PBJ-WLG-001'",
        // *** Nelson UFO Watching | Nelson ***
        CREATE_ACTIVITY, // 19
        "'Stars or Spaceships?'",
        "Scenic",
        "'NUW-NSN-001'",
        CREATE_ACTIVITY, // 20
        "'Meteorites & Meat Pies'",
        "Food",
        "'NUW-NSN-001'",
        // *** Christchurch Camel Treks | Ōtautahi | Christchurch ***
        CREATE_ACTIVITY, // 21
        "'Wild Desert Desserts'",
        "Food",
        "'CCT-CHC-001'",
        // *** Avon River Whitewater Rafting | Ōtautahi | Christchurch ***
        CREATE_ACTIVITY, // 22
        "'Rapid Riverside Ramen'",
        "Food",
        "'ARWR-CHC-002'",
        CREATE_ACTIVITY, // 23
        "'Duck Dodging'",
        "Wildlife",
        "'ARWR-CHC-002'",
        CREATE_ACTIVITY, // 24
        "'River Rush'",
        "Adventure",
        "'ARWR-CHC-002'",
        // *** Dunedin Penguin Parade | Ōtepoti | Dunedin ***
        CREATE_ACTIVITY, // 25
        "'Penguin Pies'",
        "Food",
        "'DPP-DUD-001'",
        CREATE_ACTIVITY, // 26
        "'Waddling Wonders'",
        "Wildlife",
        "'DPP-DUD-001'",
        CREATE_ACTIVITY, // 27
        "'Snowy Slide'",
        "Adventure",
        "'DPP-DUD-001'",
        // *** Baldwin Street Ski Jumping | Ōtepoti | Dunedin ***
        // none
      };

  private static Object[] unpack(Object[] commands, Object... more) {
    List<Object> all = new ArrayList<Object>();
    all.addAll(List.of(commands));

    for (Object item : more) {
      // String[] are options for certain commands, so treat as a single item
      if (item instanceof Object[] && !(item instanceof String[])) {
        all.addAll(Arrays.asList((Object[]) item));
      } else {
        all.add(item);
      }
    }
    return all.toArray(new Object[0]);
  }

  private static String[] options(String... options) {
    List<String> all = new ArrayList<String>();
    all.addAll(List.of(options));
    return all.toArray(new String[all.size()]);
  }
}
