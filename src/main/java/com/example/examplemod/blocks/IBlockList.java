package com.example.examplemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.audio.Sound;
import net.minecraft.potion.Potion;

public interface IBlockList {
    float RUBY_BLOCK_HARDNESS = 2.0f;
    float RUBY_BLOCK_RESISTANCE = 6.0f;
    SoundType RUBY_BLOCK_SOUND = SoundType.METAL;
    String RUBY_BLOCK_REGISTRY_NAME = "ruby_block";

    float CONSTRUCTION_BLOCK_HARDNESS = 5.0f;
    float CONSTRUCTION_BLOCK_RESISTANCE = 2.0f;
    SoundType CONSTRUCTION_BLOCK_SOUND = SoundType.METAL;
    String CONSTRUCTION_BLOCK_REGISTRY_NAME = "construction_block";
}
