package net.superfastscyphozoa.chellsmod.client.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Environment(EnvType.CLIENT)
public class FlyModel extends EntityModel<LivingEntityRenderState> {
    private final ModelPart body;
    private final ModelPart thorax;
    private final ModelPart abdomen;
    private final ModelPart legs;
    private final ModelPart mouth;
    private final ModelPart lwing;
    private final ModelPart rwing;

    public FlyModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.thorax = body.getChild("thorax");
        this.abdomen = thorax.getChild("abdomen");
        this.legs = thorax.getChild("legs");
        this.mouth = thorax.getChild("mouth");
        this.lwing = thorax.getChild("lwing");
        this.rwing = thorax.getChild("rwing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition modelPartData = meshDefinition.getRoot();

        CubeDeformation cubeDeformation = new CubeDeformation(0.0F);

        PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(-1.0F, 21.0F, 0.0F));

        PartDefinition thorax = body.addOrReplaceChild("thorax", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -5.0F, 6.0F, 6.0F, 7.0F, cubeDeformation), PartPose.offset(1.0F, -6.0F, 0.0F));

        PartDefinition abdomen = thorax.addOrReplaceChild("abdomen", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 4.0F, 3.0F, cubeDeformation), PartPose.offset(0.0F, 6.0F, 7.0F));

        PartDefinition legs = thorax.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(-1.0F, 6.0F, 0.0F));

        PartDefinition cube_r1 = legs.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 13).addBox(-2.0F, 0.0F, 0.0F, 6.0F, 3.0F, 0.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r2 = legs.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 13).addBox(-2.0F, 0.0F, 0.0F, 6.0F, 3.0F, 0.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, 0.0F, -1.5F, 0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r3 = legs.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(14, 17).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 3.0F, 0.0F, cubeDeformation), PartPose.offsetAndRotation(1.0F, 0.0F, -4.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition mouth = thorax.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 17).addBox(1.0F, -1.0F, -7.0F, 0.0F, 4.0F, 3.0F, cubeDeformation), PartPose.offset(-1.0F, 6.0F, 0.0F));

        PartDefinition lwing = thorax.addOrReplaceChild("lwing", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -3.0F));

        PartDefinition cube_r4 = lwing.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(-4, 20).addBox(0.0F, 0.0F, -1.0F, 6.0F, 0.0F, 10.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -0.2F, 1.0F, 0.1745F, 0.2618F, 0.0F));

        PartDefinition rwing = thorax.addOrReplaceChild("rwing", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, -3.0F));

        PartDefinition cube_r5 = rwing.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(12, 20).addBox(-6.0F, 0.0F, -1.0F, 6.0F, 0.0F, 10.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -0.2F, 1.0F, 0.1745F, -0.2618F, 0.0F));

        return LayerDefinition.create(meshDefinition, 48, 32);
    }
}
