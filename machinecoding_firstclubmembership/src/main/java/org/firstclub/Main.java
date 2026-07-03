package org.firstclub;

import org.firstclub.benefits.DiscountBenefit;
import org.firstclub.benefits.EarlyAccessBenefit;
import org.firstclub.benefits.FreeDeliveryBenefit;
import org.firstclub.benefits.PrioritySupportBenefit;
import org.firstclub.membership.Membership;
import org.firstclub.membership.MembershipRepository;
import org.firstclub.membership.UserMembership;
import org.firstclub.membership.UserMembershipRepository;
import org.firstclub.plan.PlanDAOService;
import org.firstclub.tier.Tier;
import org.firstclub.tier.TierDAOService;
import org.firstclub.tier.TierEvaluationService;
import org.firstclub.user.Role;
import org.firstclub.user.User;
import org.firstclub.plan.Plan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        PlanDAOService planDAO = new PlanDAOService();
        TierDAOService tierDAO = new TierDAOService();
        MembershipRepository membershipDAO = new MembershipRepository();
        UserMembershipRepository userMembershipDAO = new UserMembershipRepository();

        User rohit = new User("Rohit");
        User amit = new User("Amit");

        // get the plan
        Plan monthly = planDAO.findByName("MONTHLY").get();
        // get the tier
        Tier silver = tierDAO.findByName("SILVER").get();

        // create a membership using the plan and the tier
        Membership membership = new Membership(monthly, silver, LocalDate.now());
        membershipDAO.save(membership);

        // attach users to the membership
        userMembershipDAO.save(new UserMembership(rohit.getId(), membership.getId(), Role.ADMIN));
        userMembershipDAO.save(new UserMembership(amit.getId(), membership.getId(), Role.MEMBER));

        System.out.println("Membership created successfully.");

        System.out.println("\n===== Members =====");

        userMembershipDAO.findByMembershipId(membership.getId()).forEach(System.out::println);

        System.out.println("\n===== Change Tier =====");

        Tier gold = tierDAO.findByName("GOLD").get();
        membership.changeTier(gold);
        System.out.println("Current Tier : " + membership.getTierName());

        System.out.println("\n===== Change Plan =====");

        Plan yearly = planDAO.findByName("YEARLY").get();
        membership.changePlan(yearly);
        System.out.println("Current Plan : " + membership.getPlanName());

        System.out.println("\n===== Cancel Membership =====");

        membership.cancel();

        System.out.println(membership.getMembershipStatus());

        System.out.println("\n========== CUSTOM PLAN & CUSTOM TIER ==========");

        // Create a custom plan
        Plan weekendPlan = planDAO.getOrCreatePlan("WEEKEND", BigDecimal.valueOf(499), 7);

        // Create a custom tier
        Tier diamondTier = TierDAOService.getOrCreateTier(
                "DIAMOND",
                new ArrayList<>(List.of(
                        new FreeDeliveryBenefit(),
                        new DiscountBenefit(30, List.of("ALL")),
                        new EarlyAccessBenefit()
                )),0,0
        );

        // Create users
        User raj = new User("Raj");
        User priya = new User("Priya");
        User karan = new User("Karan");

        // Create membership
        Membership familyMembership = new Membership(weekendPlan, diamondTier, LocalDate.now());
        membershipDAO.save(familyMembership);

        // Attach users
        userMembershipDAO.save(new UserMembership(raj.getId(), familyMembership.getId(), Role.ADMIN));
        userMembershipDAO.save(new UserMembership(priya.getId(), familyMembership.getId(), Role.MEMBER));
        userMembershipDAO.save(new UserMembership(karan.getId(), familyMembership.getId(), Role.MEMBER));

        System.out.println("Custom Membership Created Successfully.");

        System.out.println("\nMembers:");

        userMembershipDAO.findByMembershipId(familyMembership.getId()).forEach(System.out::println);

        System.out.println("\nPlan : " + familyMembership.getPlanName());

        System.out.println("Tier : " + familyMembership.getTierName());

        System.out.println("\n========== RUNTIME TIER CONFIGURATION ==========");

        System.out.println("Benefits of DIAMOND tier before update:");

        diamondTier.getBenefits().forEach(benefit -> System.out.println(benefit.getDescription()));

        // Add a new benefit at runtime
        diamondTier.addBenefit(new PrioritySupportBenefit());

        System.out.println("\nBenefits DIAMOND tier after update:");

        diamondTier.getBenefits().forEach(benefit -> System.out.println(benefit.getDescription()));

        System.out.println("\n========== CONCURRENT TIER CREATION ==========");

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {

            executor.submit(() -> {

                Tier tier = TierDAOService.getOrCreateTier("DIAMOND",
                        List.of(
                                new FreeDeliveryBenefit(),
                                new DiscountBenefit(30, List.of("ALL"))
                        ),0,0);

                System.out.println(Thread.currentThread().getName() + " -> " + tier.getName() + " -> " + tier.getId());
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("\n========== TRY ASSIGNING SAME USER TO MULTIPLE MEMBERSHIPS ==========");

        ExecutorService executor2 = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 5; i++) {

            executor2.submit(() -> {

                try {
                    Membership m = new Membership(monthly, silver, LocalDate.now());
                    membershipDAO.save(m);
                    userMembershipDAO.save(new UserMembership(rohit.getId(), membership.getId(), Role.ADMIN));
                    System.out.println("SUCCESS");
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            });
        }

        executor2.shutdown();
        executor2.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("\n========== USERS MOVE THROUGH TIERS BASED ON CRITERIA " +
                "LIKE ORDER COUNT AND ORDER VALUE ==========");

        rohit.setOrderCount(10);
        rohit.setMonthlyOrderValue(5000);

        TierEvaluationService tierEvaluationService = new TierEvaluationService(tierDAO);
        Tier evaluatedTier = tierEvaluationService.evaluate(rohit);
        membership.changeTier(evaluatedTier);

        System.out.println("Tier after evaluation : " + membership.getTierName());

        rohit.setOrderCount(150);
        rohit.setMonthlyOrderValue(200000);

        membership.changeTier(tierEvaluationService.evaluate(rohit));

        System.out.println("Tier after evaluation : " + membership.getTierName());

        System.out.println("\n========== END ==========");
    }
}